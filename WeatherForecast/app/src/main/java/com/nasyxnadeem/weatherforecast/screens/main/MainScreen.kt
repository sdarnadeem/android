package com.nasyxnadeem.weatherforecast.screens.main


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nasyxnadeem.weatherforecast.R
import com.nasyxnadeem.weatherforecast.data.DataOrException
import com.nasyxnadeem.weatherforecast.model.City
import com.nasyxnadeem.weatherforecast.model.Weather
import com.nasyxnadeem.weatherforecast.model.WeatherItem
import com.nasyxnadeem.weatherforecast.utils.formatDate
import com.nasyxnadeem.weatherforecast.utils.formatDateTime
import com.nasyxnadeem.weatherforecast.utils.formatDecimals
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
    } else if (weatherData.data == null) {
        Text(text = "data is null", style = MaterialTheme.typography.h2)
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
    val firstItem = data.list[0]
    val imageUrl = "http://openweathermap.org/img/wn/${firstItem.weather[0].icon}@2x.png"

    val date = formatDate(firstItem.dt)
    val temp = formatDecimals(firstItem.main.temp) + "°"
    val condition = firstItem.weather[0].main



    Column(
        modifier = Modifier.padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Text(
            text = date,
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

                Text(
                    text = temp,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(text = condition, fontStyle = FontStyle.Italic)

            }
        }
        HumidityWindPressureRow(weather = firstItem)
        SunsetSunriseRow(city = data.city)

        Text(
            text = "This week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        WeatherDetailRow(weatherList = data.list)


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

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier.padding(12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // ROW1

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.main.humidity}%", style = MaterialTheme.typography.caption)
        }

        // ROW2

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.main.pressure}psi", style = MaterialTheme.typography.caption)
        }

        // ROW3
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(
                    id = R.drawable.wind
                ),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.wind.speed}mph", style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun SunsetSunriseRow(city: City) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 15.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(25.dp)
            )
            Text(text = formatDateTime(city.sunrise), style = MaterialTheme.typography.caption)
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier.size(25.dp)
            )
            Text(text = formatDateTime(city.sunset), style = MaterialTheme.typography.caption)
        }

    }
}

@Composable
fun WeatherDetailRow(weatherList: List<WeatherItem>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xffeef1ef),
        shape = RoundedCornerShape(size = 14.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)
        ) {
            items(items = weatherList) { item ->
                WeatherDetailRowItem(weather = item)

            }
        }
    }
}

@Composable
fun WeatherDetailRowItem(weather: WeatherItem) {
    val imageUrl = "http://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"

    Surface(
        modifier = Modifier.padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = formatDate(weather.dt).split(",")[0], modifier = Modifier.padding(start = 5.dp))

            WeatherStateImage(imageUrl)

            Surface(modifier = Modifier.padding(0.dp), shape = CircleShape, color = Color(0xffffc400)) {
                Text(text = weather.weather[0].description, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }
        }
    }
}

