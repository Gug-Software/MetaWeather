package com.jkgug.bold.metaweather.weather.search.repository

import com.xwray.groupie.Item

interface ISearchRepository {
    suspend fun searchByQuery(text: String): List<Item<*>>
}