package com.jkgug.bold.metaweather.remote.api.model

data class Source(
    val crawl_rate: Int,
    val slug: String,
    val title: String,
    val url: String
)