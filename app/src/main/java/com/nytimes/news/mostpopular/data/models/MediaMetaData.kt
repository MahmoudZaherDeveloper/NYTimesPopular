package com.nytimes.news.mostpopular.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaMetaData(
    @SerializedName("url")
    var url: String,
    @SerializedName("format")
    var format: String,
    @SerializedName("height")
    var height: Int,
    @SerializedName("width")
    var width: Int
) : Parcelable