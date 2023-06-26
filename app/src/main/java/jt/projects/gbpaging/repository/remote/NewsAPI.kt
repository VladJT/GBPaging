package jt.projects.gbpaging.repository.remote

import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.repository.dto.NewsDTO
import retrofit2.http.GET

interface NewsAPI {
    @GET("r/aww/hot.json")
    suspend fun getNews(): NewsDTO
}
