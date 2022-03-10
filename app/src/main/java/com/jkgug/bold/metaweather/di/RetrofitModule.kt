package com.jkgug.bold.metaweather.di

import com.jkgug.bold.metaweather.remote.api.APIMetaWeather
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val URL = "https://www.metaweather.com/api/"

val dataRemoteModule = module {

    factory { provideMoshi() }
    factory { provideOkHttpClient() }

    single {
        provideRetrofit(
            okHttpClient = get(),
            moshi = get()
        )
    }

    factory { provideAPIMetaWeather(get()) }

}

fun provideAPIMetaWeather(retrofit: Retrofit): APIMetaWeather =
    retrofit.create(APIMetaWeather::class.java)

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}