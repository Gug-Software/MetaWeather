package com.jkgug.bold.metaweather.splash.viewmodel

import androidx.lifecycle.*
import com.jkgug.bold.metaweather.base.BaseViewModelCoroutines
import com.jkgug.bold.metaweather.weather.search.repository.ISearchRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModelCoroutines(), LifecycleObserver {

    private val _uiModel = MutableLiveData<SplashUiModel>()
    val uiModel: LiveData<SplashUiModel>
        get() = _uiModel

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        _uiModel.value = SplashUiModel.Loading
        uiScope.launch {
            delay(3000)
            _uiModel.value = SplashUiModel.Next
        }
    }
}