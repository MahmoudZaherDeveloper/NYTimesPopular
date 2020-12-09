package com.nytimes.news.base.caching

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nytimes.news.mostpopular.data.local.NewsArticleDao
import com.nytimes.news.mostpopular.data.local.NewsArticleEntity

@Database(entities = [NewsArticleEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun newsArticleDao(): NewsArticleDao?
}