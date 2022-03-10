package com.jkgug.bold.metaweather.remote.contracts.search

import com.jkgug.bold.metaweather.remote.api.APIMetaWeather
import com.jkgug.bold.metaweather.remote.api.model.DtoOutSearchResultItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSearch(
    private val api: APIMetaWeather,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IRemoteSearch {

    override suspend fun searchByQuery(text: String): List<DtoOutSearchResultItem> {
        return withContext(ioDispatcher) {
            return@withContext try {
                api.searchByQuery(text)
            } catch (exception: Exception) {
                throw exception
            }
        }
    }
}