package jt.projects.gbpaging.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import jt.projects.gbpaging.intercators.NewsInteractor
import jt.projects.gbpaging.repository.local.INewsLocalRepo
import jt.projects.gbpaging.repository.local.NewsDao
import jt.projects.gbpaging.repository.local.NewsLocalDataSource
import jt.projects.gbpaging.repository.remote.INewsRemoteRepo
import jt.projects.gbpaging.repository.remote.NewsRemoteDataSource
import jt.projects.gbpaging.ui.MainActivity
import javax.inject.Singleton


@Module
class MainModule {
    @Provides
    @Singleton
    fun remoteRepo(): INewsRemoteRepo = NewsRemoteDataSource()

    @Provides
    @Singleton
    fun localRepo(dao: NewsDao): INewsLocalRepo = NewsLocalDataSource(dao)

    @Provides
    @Singleton
    fun newsInteractor(localRepo: INewsLocalRepo, remoteRepo: INewsRemoteRepo): NewsInteractor =
        NewsInteractor(localRepo, remoteRepo)
}

/**
 * Модуль для Activity. Так как мы используем дополнительную библиотеку поддержки для Android, то все
становится гораздо проще при помощи ContributesAndroidInjector. Он позволяет внедрять
зависимости в Activity (нашу ViewModel) благодаря простому AndroidInjection.inject(this) в методе
onCreate
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}