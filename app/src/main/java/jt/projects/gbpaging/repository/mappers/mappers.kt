package jt.projects.gbpaging.repository.mappers

import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.repository.dto.NewsDTO
import jt.projects.gbpaging.repository.local.NewsEntity

fun NewsDTO.toNewsList(): List<News> {
    val news = mutableListOf<News>()
    this.d.children.forEach {
        news.add(News(title = it.d.title, stars = it.d.score, comments = it.d.num_comments))
    }
    return news
}

fun NewsEntity.toNews(): News {
    return News(id = this.id, title = this.title, stars = this.stars, comments = this.comments)
}

fun News.toNewsEntity(): NewsEntity {
    // id - autogenerate
    return NewsEntity(title = this.title, stars = this.stars, comments = this.comments)
}