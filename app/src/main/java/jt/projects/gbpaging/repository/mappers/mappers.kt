package jt.projects.gbpaging.repository.mappers

import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.repository.dto.NewsDTO
import jt.projects.gbpaging.repository.local.NewsEntity

fun NewsDTO.toNewsList(): List<News>{
    val news = mutableListOf<News>()
    this.d.children.forEach {
        news.add(News(it.d.title, it.d.score, it.d.num_comments))
    }
    return news
}

fun NewsEntity.toNews(): News {
    return News(this.title, this.stars, this.comments)
}