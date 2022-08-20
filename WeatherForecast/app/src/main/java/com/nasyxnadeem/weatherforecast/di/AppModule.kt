package com.nasyxnadeem.weatherforecast.di

import android.content.Context
import androidx.room.Room
import com.nasyxnadeem.weatherforecast.data.WeatherDao
import com.nasyxnadeem.weatherforecast.data.WeatherDatabase
import com.nasyxnadeem.weatherforecast.network.WeatherAPI
import com.nasyxnadeem.weatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// create the providers, that provide the app with resources required or dependency injection
@Module
@InstallIn(SingletonComponent::class)
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

    // Provides a room database dao
    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao = weatherDatabase.weatherDao()

    // Provides a room database instance

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : WeatherDatabase = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database"
    ).fallbackToDestructiveMigration()
        .build()
}