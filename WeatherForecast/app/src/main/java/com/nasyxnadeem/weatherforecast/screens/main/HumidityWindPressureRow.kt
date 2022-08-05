package com.nasyxnadeem.weatherforecast.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nasyxnadeem.weatherforecast.R
import com.nasyxnadeem.weatherforecast.model.WeatherItem

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
