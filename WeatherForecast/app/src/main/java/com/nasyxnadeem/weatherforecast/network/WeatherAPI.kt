package com.nasyxnadeem.weatherforecast.network

import com.nasyxnadeem.weatherforecast.model.WeatherObject
import com.nasyxnadeem.weatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {

    // making an api call
    @GET(value = "data/2.5/forecast/")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY
    ): WeatherObject }