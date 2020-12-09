package com.nytimes.news.mostpopular.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticle(
    @SerializedName("id")
    var id: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("abstract")
    var abstract: String,
    @SerializedName("published_date")
    var publishedDate: String,
    @SerializedName("media")
    var media: List<Media>
) : Parcelable