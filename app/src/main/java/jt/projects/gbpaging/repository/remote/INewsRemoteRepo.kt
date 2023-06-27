package jt.projects.gbpaging.repository.remote

import jt.projects.gbpaging.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRemoteRepo {
    suspend fun getNews(): List<News>
}