package com.jkgug.bold.metaweather.weather.search.repository

import com.jkgug.bold.metaweather.model.SearchResult
import com.jkgug.bold.metaweather.remote.contracts.search.IRemoteSearch
import com.jkgug.bold.metaweather.weather.search.views.SearchResultItemView
import com.xwray.groupie.Item

class SearchRepository(
    private val iRemoteSearch: IRemoteSearch
) : ISearchRepository {

    override suspend fun searchByQuery(text: String): List<Item<*>> {
        val results = iRemoteSearch.searchByQuery(text)
        val resultsMap = results.map { SearchResult(it.title, it.location_type, it.woeid) }
        val items = resultsMap.map {
            SearchResultItemView(it)
        }
        return items
    }
}