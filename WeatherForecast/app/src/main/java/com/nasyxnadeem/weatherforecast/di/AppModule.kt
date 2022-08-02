package com.nasyxnadeem.weatherforecast.di

import com.nasyxnadeem.weatherforecast.network.WeatherAPI
import com.nasyxnadeem.weatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// create the providers, that provide the app with resources required
@Module
@InstallIn(Singleton::class)
class AppModule {

    // Provide function for our retrofit backed api
    @Provides
    @Singleton
    fun provideOpenWeatherAPI() : WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }
}