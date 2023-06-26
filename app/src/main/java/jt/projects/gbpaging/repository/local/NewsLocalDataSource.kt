package jt.projects.gbpaging.repository.local


import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.repository.INewsRepo
import jt.projects.gbpaging.repository.mappers.toNews
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val dao: NewsDao) : INewsRepo {

    override suspend fun getNews(): List<News> = dao.getAllNews().map {
        it.toNews()
    }


}
