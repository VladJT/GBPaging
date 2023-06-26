package jt.projects.gbpaging.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import jt.projects.gbpaging.intercators.NewsInteractor
import jt.projects.gbpaging.repository.INewsRepo
import jt.projects.gbpaging.repository.remote.NewsRemoteDataSource
import jt.projects.gbpaging.ui.MainActivity
import javax.inject.Singleton


@Module
class MainModule {
    @Provides
    @Singleton
    fun newsRepo(): INewsRepo = NewsRemoteDataSource()

    @Provides
    @Singleton
    fun newsInteractor(repo: INewsRepo): NewsInteractor = NewsInteractor(repo)
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