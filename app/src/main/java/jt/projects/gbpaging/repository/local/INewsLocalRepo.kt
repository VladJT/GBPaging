package jt.projects.gbpaging.repository.local

import androidx.paging.PagingData
import jt.projects.gbpaging.model.News
import kotlinx.coroutines.flow.Flow

interface INewsLocalRepo {
    suspend fun getNews(): List<News>
    fun getPagedNews(): Flow<PagingData<News>>

    suspend fun clearNews()
    suspend fun insertNews(news: List<News>)
}