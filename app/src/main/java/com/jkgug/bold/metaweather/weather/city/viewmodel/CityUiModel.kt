package com.jkgug.bold.metaweather.weather.city.viewmodel

import com.jkgug.bold.metaweather.model.WeatherBasicInfo
import com.xwray.groupie.Item

sealed class CityUiModel {

    object LoadingPpal : CityUiModel()

    class ShowDataCity(
        val data: Triple<WeatherBasicInfo, List<Item<*>>, List<Item<*>>>
    ) : CityUiModel()

    class ShowDataCityForToday(
        val data: List<Item<*>>
    ) : CityUiModel()

}