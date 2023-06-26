package jt.projects.gbpaging.intercators

import jt.projects.gbpaging.repository.INewsRepo


class NewsInteractor(private val repo: INewsRepo) {

    suspend fun getNews() = repo.getNews()
}