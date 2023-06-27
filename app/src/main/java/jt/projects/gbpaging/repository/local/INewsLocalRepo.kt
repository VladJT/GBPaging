package jt.projects.gbpaging.repository.local

import jt.projects.gbpaging.model.News

interface INewsLocalRepo {
    suspend fun getNews(): List<News>
    suspend fun clearNews()
    suspend fun insertNews(news: List<News>)
}