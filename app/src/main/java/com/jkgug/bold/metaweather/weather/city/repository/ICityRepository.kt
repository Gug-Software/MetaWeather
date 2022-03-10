package com.jkgug.bold.metaweather.weather.city.repository

import com.jkgug.bold.metaweather.model.WeatherBasicInfo
import com.xwray.groupie.Item

interface ICityRepository {

    suspend fun getWeatherByCityWoeId(
        woeId: String
    ): Triple<WeatherBasicInfo, List<Item<*>>, List<Item<*>>>

    suspend fun getWeatherForToday(
        woeid: String
    ): List<Item<*>>
}