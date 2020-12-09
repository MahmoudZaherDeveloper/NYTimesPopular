package com.nytimes.news.mostpopular.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class NewsArticleEntity(
    @PrimaryKey
    var period: String,
    var newsArticleData: String? = null,
    var currentDate: Long? = Date().time
) : Parcelable