package com.jkgug.bold.metaweather.remote.contracts.city

import com.jkgug.bold.metaweather.remote.api.APIMetaWeather
import com.jkgug.bold.metaweather.remote.api.model.ConsolidatedWeather
import com.jkgug.bold.metaweather.remote.api.model.DtoOutSearchResultItem
import com.jkgug.bold.metaweather.remote.api.model.DtoOutWeatherConsolidated
import com.jkgug.bold.metaweather.utils.getCurrentDate
import com.jkgug.bold.metaweather.utils.getCurrentDateForService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteCity(
    private val api: APIMetaWeather,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IRemoteCity {

    override suspend fun getWeatherByCityWoeId(woeId: String): DtoOutWeatherConsolidated {
        return withContext(ioDispatcher) {
            return@withContext try {
                api.getWeatherByCityWoeId(woeId)
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun getWeatherTodayByWoeId(woeid: String): List<ConsolidatedWeather> {
        return withContext(ioDispatcher) {
            return@withContext try {
                api.getWeatherByCityWoeIdAndDate(woeid, getCurrentDateForService())
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

}