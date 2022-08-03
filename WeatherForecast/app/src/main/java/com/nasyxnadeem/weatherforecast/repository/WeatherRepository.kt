package com.nasyxnadeem.weatherforecast.repository

import android.util.Log
import com.nasyxnadeem.weatherforecast.data.DataOrException
import com.nasyxnadeem.weatherforecast.model.Weather
import com.nasyxnadeem.weatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherAPI) {
    suspend fun getWeather(city: String, units: String = "imperial") : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(city = city, units = units)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        val TAG = "DATA3"
        Log.d(TAG, response.city.toString())
        return DataOrException(data = response)
    }
}