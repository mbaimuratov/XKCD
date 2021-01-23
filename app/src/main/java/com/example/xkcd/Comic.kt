package com.example.xkcd

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    @SerializedName("month")
    @Expose
    val month: String,
    @SerializedName("num")
    @Expose
    val num: Int,
    @SerializedName("link")
    @Expose
    val link: String,
    @SerializedName("year")
    @Expose
    val year: String,
    @SerializedName("news")
    @Expose
    val news: String,
    @SerializedName("safe_title")
    @Expose
    val safe_title: String,
    @SerializedName("transcript")
    @Expose
    val transcript: String,
    @SerializedName("alt")
    @Expose
    val alt: String,
    @SerializedName("img")
    @Expose
    val img: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("day")
    @Expose
    val day: String
) : Parcelable