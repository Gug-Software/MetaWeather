package com.jkgug.bold.metaweather.remote.api

import com.jkgug.bold.metaweather.remote.api.model.ConsolidatedWeather
import com.jkgug.bold.metaweather.remote.api.model.DtoOutSearchResultItem
import com.jkgug.bold.metaweather.remote.api.model.DtoOutWeatherConsolidated
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val SEARCH = "location/search/"
const val BY_WOEID = "location/{woeid}"
const val BY_WOEID_AND_DATE = "location/{woeid}/{date}"

interface APIMetaWeather {

    @GET(SEARCH)
    suspend fun searchByQuery(
        @Query("query") query: String
    ): List<DtoOutSearchResultItem>

    @GET(BY_WOEID)
    suspend fun getWeatherByCityWoeId(
        @Path("woeid") woeid: String
    ): DtoOutWeatherConsolidated

    @GET(BY_WOEID_AND_DATE)
    suspend fun getWeatherByCityWoeIdAndDate(
        @Path("woeid") woeid: String,
        @Path("date") date: String
    ): List<ConsolidatedWeather>

}