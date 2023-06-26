package jt.projects.gbpaging.repository

import jt.projects.gbpaging.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepo {
    suspend fun getNews(): List<News>
}