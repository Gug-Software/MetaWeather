package com.jkgug.bold.metaweather.remote.api.model

data class DtoOutSearchResultItem(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)