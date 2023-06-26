package jt.projects.gbpaging.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import jt.projects.gbpaging.App
import jt.projects.gbpaging.repository.local.NewsDao
import jt.projects.gbpaging.repository.local.NewsDatabase
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun dao(db: NewsDatabase): NewsDao = db.dao()

    @Provides
    @Singleton
    fun database(app: App): NewsDatabase =
        Room.databaseBuilder(app, NewsDatabase::class.java, "newsdb.db").build()
}