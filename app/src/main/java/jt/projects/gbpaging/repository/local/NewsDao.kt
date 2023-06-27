package jt.projects.gbpaging.repository.local

import androidx.room.*

// ключевое слово suspend, которое намекает, что все
// запросы в БД будут асинхронными (корутины поддерживаются в Room изначально)

@Dao
interface NewsDao {
    /**
     * SELECT
     */
    @Query("select * from NewsEntity")
    suspend fun getAllNews(): List<NewsEntity>

    @Query("select * from NewsEntity ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getNews(limit: Int, offset: Int): List<NewsEntity>


    /**
     * INSERT
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<NewsEntity>)


    /**
     * DELETE
     */
    @Query("delete from NewsEntity")
    suspend fun deleteAll()
}
