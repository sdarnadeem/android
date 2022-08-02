package com.nasyxnadeem.weatherforecast.repository

import com.nasyxnadeem.weatherforecast.model.WeatherObject
import com.nasyxnadeem.weatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherAPI) {
    suspend fun getWeather(city: String, units: String) : WeatherObject {
        api.getWeather(query = city, units = units )
    }
}