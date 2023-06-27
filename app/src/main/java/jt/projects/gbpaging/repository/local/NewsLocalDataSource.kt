package jt.projects.gbpaging.repository.local


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.repository.mappers.toNews
import jt.projects.gbpaging.repository.mappers.toNewsEntity
import jt.projects.gbpaging.utils.PAGE_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val dao: NewsDao) : INewsLocalRepo {

    override suspend fun getNews(): List<News> = dao.getAllNews().map {
        it.toNews()
    }

    override fun getPagedNews(): Flow<PagingData<News>> {
        val loader: NewsPageLoader = { pageIndex, pageSize ->
            delay(1000)
            dao.getNews(limit = pageSize, offset = pageIndex * pageSize).map {
                it.toNews()
            }
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { NewsPagingSource(loader, PAGE_SIZE) }
        ).flow
    }

    override suspend fun clearNews() {
        dao.deleteAll()
    }

    override suspend fun insertNews(news: List<News>) {
        dao.insertAll(news.map {
            it.toNewsEntity()
        })
    }


}
