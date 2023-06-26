package jt.projects.gbpaging.di

import dagger.Module
import dagger.Provides
import jt.projects.gbpaging.App
import javax.inject.Singleton


@Module
class AppModule(app: App) {
    private val application: App

    init {
        this.application = app
    }

    @Provides
    @Singleton
    fun application(): App {
        return application
    }
}