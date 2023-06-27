package jt.projects.gbpaging.repository.local


import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.repository.mappers.toNews
import jt.projects.gbpaging.repository.mappers.toNewsEntity
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val dao: NewsDao) : INewsLocalRepo {

    override suspend fun getNews(): List<News> = dao.getAllNews().map {
        it.toNews()
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
