package com.jkgug.bold.metaweather.app

import android.app.Application
import com.jkgug.bold.metaweather.di.dataRemoteModule
import com.jkgug.bold.metaweather.di.remoteContractsModule
import com.jkgug.bold.metaweather.di.repositoriesModule
import com.jkgug.bold.metaweather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MetaWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            //inject Android context
            androidContext(this@MetaWeatherApplication)

            modules(viewModelModule)
            modules(repositoriesModule)
            modules(dataRemoteModule)
            modules(remoteContractsModule)

        }
    }
}