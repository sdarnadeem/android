package com.nasyxnadeem.weatherforecast.screens.main

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import com.nasyxnadeem.weatherforecast.data.DataOrException
import com.nasyxnadeem.weatherforecast.model.Weather

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = viewModel.getWeatherData(city = "srinagar", units = "imperial")
    }.value

    val TAG = "DATA2"
    Log.d(TAG, "MainScreen: ${weatherData.data?.city}")
}