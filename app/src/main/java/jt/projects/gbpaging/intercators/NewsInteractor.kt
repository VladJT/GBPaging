package jt.projects.gbpaging.intercators

import jt.projects.gbpaging.repository.local.INewsLocalRepo
import jt.projects.gbpaging.repository.remote.INewsRemoteRepo


class NewsInteractor(
    private val localRepo: INewsLocalRepo,
    private val remoteRepo: INewsRemoteRepo
) {

    suspend fun getNewsFromRemoteSource() = remoteRepo.getNews()

    suspend fun getNewsFromLocalSource() = localRepo.getNews()

    suspend fun clearLocalSource() = localRepo.clearNews()

    suspend fun fillLocalDataSourceFromRemote() {
        val news = remoteRepo.getNews()
        localRepo.insertNews(news)
    }

    fun getPagedNews() = localRepo.getPagedNews()
}