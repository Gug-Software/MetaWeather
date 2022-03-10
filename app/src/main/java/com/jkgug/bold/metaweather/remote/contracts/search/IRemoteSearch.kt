package com.jkgug.bold.metaweather.remote.contracts.search

import com.jkgug.bold.metaweather.remote.api.model.DtoOutSearchResultItem

interface IRemoteSearch {
    suspend fun searchByQuery(text: String): List<DtoOutSearchResultItem>
}