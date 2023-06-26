package jt.projects.gbpaging.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jt.projects.gbpaging.ui.MainActivity


@Module
class MainModule {

//    @Provides
//    @Singleton
//    fun lessonRepo(): ILessonsRepo = LessonsFakeRepo()
//
//    @Provides
//    @Singleton
//    fun homeworkRepo(): IHomeworkRepo = HomeworkFakeRepo()
//
//    @Provides
//    @Singleton
//    fun lessonInteractor(repo: ILessonsRepo): LessonInteractor = LessonInteractor(repo)
//
//    @Provides
//    @Singleton
//    fun homeworkInteractor(repo: IHomeworkRepo): HomeworkInteractor = HomeworkInteractor(repo)
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