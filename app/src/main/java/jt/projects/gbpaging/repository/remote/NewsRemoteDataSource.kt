package jt.projects.gbpaging.repository.remote


import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.repository.mappers.toNewsList
import jt.projects.gbpaging.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRemoteDataSource : INewsRemoteRepo {

    private inline fun <reified T> getApi(): T =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //   .addCallAdapterFactory(AdapterFa.create())
            .client(createOkHttpClient(BaseInterceptor.interceptor))
            .build()
            .create(T::class.java)


    override suspend fun getNews(): List<News> = getApi<NewsAPI>().getNews().toNewsList()

    /**
     *     В библиотеку можно внедрить перехватчики для изменения заголовков при помощи класса Interceptor из OkHttp.
    Сначала следует создать объект перехватчика и передать его в OkHttp, который в свою очередь следует явно подключить в
    Retrofit.Builder через метод client().
     */
    private fun createOkHttpClient(interceptor: Interceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()


}
