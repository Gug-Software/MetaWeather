package com.jkgug.bold.metaweather.weather.city.viewmodel

import androidx.lifecycle.*
import com.jkgug.bold.metaweather.base.BaseViewModelCoroutines
import com.jkgug.bold.metaweather.weather.city.repository.ICityRepository
import com.jkgug.bold.metaweather.weather.search.repository.ISearchRepository
import kotlinx.coroutines.launch

class CityViewModel(
    private val iCityRepository: ICityRepository
) : BaseViewModelCoroutines(), LifecycleObserver {

    private val _uiModel = MutableLiveData<CityUiModel>()
    val uiModel: LiveData<CityUiModel>
        get() = _uiModel

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        _uiModel.value = CityUiModel.LoadingPpal
    }

    fun getData(woeid: String) {
        uiScope.launch {
            try {
                val data = iCityRepository.getWeatherByCityWoeId(woeId = woeid)
                _uiModel.value = CityUiModel.ShowDataCity(data)

                val dayData = iCityRepository.getWeatherForToday(woeid)
                _uiModel.value = CityUiModel.ShowDataCityForToday(dayData)
            } catch (exc: Exception) {
                _uiModel.value = CityUiModel.Error
            }
        }
    }

}