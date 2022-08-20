package com.nasyxnadeem.weatherforecast.screens.main


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nasyxnadeem.weatherforecast.components.WeatherStateImage
import com.nasyxnadeem.weatherforecast.data.DataOrException
import com.nasyxnadeem.weatherforecast.model.Weather
import com.nasyxnadeem.weatherforecast.navigation.WeatherScreens
import com.nasyxnadeem.weatherforecast.screens.setting.SettingViewModel
import com.nasyxnadeem.weatherforecast.utils.formatDate
import com.nasyxnadeem.weatherforecast.utils.formatDecimals
import com.nasyxnadeem.weatherforecast.widgets.WeatherTopBar

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel, city : String?, settingViewModel: SettingViewModel = hiltViewModel()) {

    val unitFromDb = settingViewModel.unitList.collectAsState().value

    var unit by remember {
        mutableStateOf("imperial")
    }

    var isImperial by remember {
        mutableStateOf(false)
    }

    if (!unitFromDb.isNullOrEmpty()) {
        unit = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial = unit == "imperial"
    }

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getWeatherData(city = city.toString(), units = unit)
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
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            }
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










