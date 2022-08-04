package com.nasyxnadeem.weatherforecast.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nasyxnadeem.weatherforecast.data.DataOrException
import com.nasyxnadeem.weatherforecast.model.Weather
import com.nasyxnadeem.weatherforecast.widgets.WeatherTopBar

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getWeatherData(city = "srinagar", units = "imperial")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController = navController)
    }

}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {

    Scaffold(topBar = {
        WeatherTopBar(
            title = weather.city.name + ",${weather.city.country}",
            isMainScreen = true,
            navController = navController
        )

    }) {

        MainContent(data = weather)
        val dil = it
    }

}

@Composable
fun MainContent(data: Weather) {
    val imageUrl = "http://openweathermap.org/img/wn/${data.list[0].weather[0].icon}@2x.png"

    val date = data.list[0].dt.toString()


    Column(
        modifier = Modifier.padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Nov 29",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier.padding(4.dp).size(200.dp),
            shape = CircleShape,
            color = Color(0xffffc400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image
                WeatherStateImage(imageUrl = imageUrl)

                Text(text = "56", style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)

                Text(text = "snow", fontStyle = FontStyle.Italic)
            }
        }


    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier.size(80.dp)
    )
}

