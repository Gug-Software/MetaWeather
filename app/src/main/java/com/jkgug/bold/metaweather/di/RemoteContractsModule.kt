package com.jkgug.bold.metaweather.di

import com.jkgug.bold.metaweather.remote.contracts.city.IRemoteCity
import com.jkgug.bold.metaweather.remote.contracts.city.RemoteCity
import com.jkgug.bold.metaweather.remote.contracts.search.IRemoteSearch
import com.jkgug.bold.metaweather.remote.contracts.search.RemoteSearch
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val remoteContractsModule = module {

    single<IRemoteSearch> {
        RemoteSearch(
            api = get(),
            ioDispatcher = Dispatchers.IO
        )
    }

    single<IRemoteCity> {
        RemoteCity(
            api = get(),
            ioDispatcher = Dispatchers.IO
        )
    }

}