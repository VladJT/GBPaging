package jt.projects.gbpaging.repository.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jt.projects.gbpaging.model.News

typealias NewsPageLoader = suspend (pageIndex: Int, pageSize: Int) -> List<News>

class NewsPagingSource(
    private val loader: NewsPageLoader,
    private val pageSize: Int
) : PagingSource<Int, News>() {

    // возвращает индекс страницы, после того как данные стали невалидны
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        val anchorPos = state.anchorPosition ?: return null
        // вычисляем ближающую страницу для обновления данных
        val page = state.closestPageToPosition(anchorPos) ?: return null
        // вернет индекс страницы после инвалидации данных
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        // индекс страницы
        val pageIndex = params.key ?: 0

        return try {
            val news = loader.invoke(pageIndex, params.loadSize)

            return LoadResult.Page(
                data = news,
                // предыдущий аргумент пагинации
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                // следующий аргумент пагинации, + (params.loadSize / pageSize) нужно т.к. первая загрузка идет обычно x3
                nextKey = if (news.size == params.loadSize) pageIndex + (params.loadSize / pageSize) else null
            )
        } catch (e: Exception) {
            // failed load news
            LoadResult.Error(throwable = e)
        }
    }
}