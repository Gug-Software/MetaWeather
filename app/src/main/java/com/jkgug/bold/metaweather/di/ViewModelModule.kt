package com.jkgug.bold.metaweather.di

import com.jkgug.bold.metaweather.splash.viewmodel.SplashViewModel
import com.jkgug.bold.metaweather.weather.city.viewmodel.CityViewModel
import com.jkgug.bold.metaweather.weather.search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SearchViewModel(searchRepository = get())
    }

    viewModel {
        CityViewModel(iCityRepository = get())
    }

    viewModel {
        SplashViewModel()
    }

}