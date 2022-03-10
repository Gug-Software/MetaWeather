package com.jkgug.bold.metaweather.di

import com.jkgug.bold.metaweather.weather.city.repository.CityRepository
import com.jkgug.bold.metaweather.weather.city.repository.ICityRepository
import com.jkgug.bold.metaweather.weather.search.repository.ISearchRepository
import com.jkgug.bold.metaweather.weather.search.repository.SearchRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single<ISearchRepository> {
        SearchRepository(iRemoteSearch = get())
    }

    single<ICityRepository> {
        CityRepository(iRemoteCity = get())
    }

}