package com.jkgug.bold.metaweather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(
    val title: String,
    val type: String,
    val woeid: Int
) : Parcelable
