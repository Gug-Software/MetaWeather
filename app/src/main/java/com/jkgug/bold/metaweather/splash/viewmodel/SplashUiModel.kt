package com.jkgug.bold.metaweather.splash.viewmodel

sealed class SplashUiModel {

    object Loading : SplashUiModel()
    object Next : SplashUiModel()

}