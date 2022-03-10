package com.jkgug.bold.metaweather.remote.contracts.city

import com.jkgug.bold.metaweather.remote.api.model.ConsolidatedWeather
import com.jkgug.bold.metaweather.remote.api.model.DtoOutWeatherConsolidated

interface IRemoteCity {

    suspend fun getWeatherByCityWoeId(
        woeId: String
    ): DtoOutWeatherConsolidated

    suspend fun getWeatherTodayByWoeId(
        woeid: String
    ): List<ConsolidatedWeather>

}