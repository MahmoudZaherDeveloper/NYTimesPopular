package com.nytimes.news.mostpopular.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: NewsArticleEntity)

    @Query("SELECT * from NewsArticleEntity WHERE period= :period")
    fun getAllNews(period: String): NewsArticleEntity
}