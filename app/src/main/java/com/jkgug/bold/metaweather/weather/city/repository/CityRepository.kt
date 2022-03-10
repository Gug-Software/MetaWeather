package com.jkgug.bold.metaweather.weather.city.repository

import com.jkgug.bold.metaweather.model.WeatherAdditionalInfo
import com.jkgug.bold.metaweather.model.WeatherBasicInfo
import com.jkgug.bold.metaweather.model.WeatherFutureInfo
import com.jkgug.bold.metaweather.remote.api.model.ConsolidatedWeather
import com.jkgug.bold.metaweather.remote.api.model.DtoOutWeatherConsolidated
import com.jkgug.bold.metaweather.remote.contracts.city.IRemoteCity
import com.jkgug.bold.metaweather.utils.*
import com.jkgug.bold.metaweather.weather.city.views.groupie.WeatherAdditionInfoItemView
import com.jkgug.bold.metaweather.weather.city.views.groupie.WeatherFutureItemView
import com.xwray.groupie.Item
import kotlin.math.roundToInt

const val URL_IMAGE = "https://www.metaweather.com/static/img/weather/png/REPLACE.png"
const val URL_IMAGE_64 = "https://www.metaweather.com/static/img/weather/png/64/REPLACE.png"

class CityRepository(
    private val iRemoteCity: IRemoteCity
) : ICityRepository {

    override suspend fun getWeatherByCityWoeId(
        woeId: String
    ): Triple<WeatherBasicInfo, List<Item<*>>, List<Item<*>>> {
        val consolidatedWeather = iRemoteCity.getWeatherByCityWoeId(woeId = woeId)
        val (basicInfo, listItems, listItemsFuture) = getTripleDataForViewModel(consolidatedWeather)
        return Triple(basicInfo, listItems, listItemsFuture)
    }

    override suspend fun getWeatherForToday(woeid: String): List<Item<*>> {
        val consolidateForToday = iRemoteCity.getWeatherTodayByWoeId(woeid)
        val consolidateForTodayOrder = consolidateForToday.sortedBy { it.created }
        return consolidateForTodayOrder.map {
            WeatherFutureItemView(
                info = WeatherFutureInfo(
                    title = it.created.transformDateToFormatHour(),
                    urlIcon = getSmallIconUrl(it.weather_state_abbr),
                    value = it.the_temp.roundToInt().toString().toCelsius()
                )
            )
        }
    }

    private fun getTripleDataForViewModel(
        consolidatedWeather: DtoOutWeatherConsolidated
    ): Triple<WeatherBasicInfo, MutableList<Item<*>>, List<WeatherFutureItemView>> {
        val currentConsolidated =
            consolidatedWeather.consolidated_weather.first { it.applicable_date == getCurrentDate() }
        val basicInfo = getWeatherBasicInfo(currentConsolidated)
        val listItems = getListWeatherAdditionalInfo(currentConsolidated)
        val sizeList = consolidatedWeather.consolidated_weather.size
        val otherConsolidated = consolidatedWeather.consolidated_weather.subList(1, sizeList - 1)
        val listItemsFuture = getListWeatherFuture(otherConsolidated)
        return Triple(basicInfo, listItems, listItemsFuture)
    }

    private fun getWeatherBasicInfo(currentConsolidated: ConsolidatedWeather) =
        WeatherBasicInfo(
            weatherStateName = currentConsolidated.weather_state_name,
            weatherStateAbbr = currentConsolidated.weather_state_abbr,
            weatherIconUrl = getIconUrl(currentConsolidated.weather_state_abbr)
        )

    private fun getListWeatherAdditionalInfo(currentConsolidated: ConsolidatedWeather): MutableList<Item<*>> {
        /**
         * Por acá debia inyectar algo como un resourceAlgo que me entregará desde el file strings...
         */
        val listItems = mutableListOf<Item<*>>()
        listItems.add(
            WeatherAdditionInfoItemView(
                WeatherAdditionalInfo(
                    title = "Viento",
                    value = currentConsolidated.wind_speed.roundToInt().toString().toKms()
                )
            )
        )
        listItems.add(
            WeatherAdditionInfoItemView(
                WeatherAdditionalInfo(
                    title = "Humedad", value = currentConsolidated.humidity.toString().toPercent()
                )
            )
        )
        listItems.add(
            WeatherAdditionInfoItemView(
                WeatherAdditionalInfo(
                    title = "Temp.",
                    value = currentConsolidated.the_temp.roundToInt().toString().toCelsius()
                )
            )
        )
        return listItems
    }

    private fun getListWeatherFuture(otherConsolidated: List<ConsolidatedWeather>): List<WeatherFutureItemView> {
        return otherConsolidated.map {
            WeatherFutureItemView(
                info = WeatherFutureInfo(
                    title = it.applicable_date.transformDateToFormatDayName(),
                    urlIcon = getSmallIconUrl(it.weather_state_abbr),
                    value = it.the_temp.roundToInt().toString().toCelsius()
                )
            )
        }
    }

    private fun getIconUrl(weatherStateAbbr: String): String {
        return URL_IMAGE.replace("REPLACE", weatherStateAbbr)
    }

    private fun getSmallIconUrl(weatherStateAbbr: String): String {
        return URL_IMAGE_64.replace("REPLACE", weatherStateAbbr)
    }
}