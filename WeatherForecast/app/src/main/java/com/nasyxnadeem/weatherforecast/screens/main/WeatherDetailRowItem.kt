package com.nasyxnadeem.weatherforecast.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.nasyxnadeem.weatherforecast.components.WeatherStateImage
import com.nasyxnadeem.weatherforecast.model.WeatherItem
import com.nasyxnadeem.weatherforecast.utils.formatDate
import com.nasyxnadeem.weatherforecast.utils.formatDecimals

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
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp,  end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = formatDate(weather.dt).split(",")[0], modifier = Modifier.padding(start = 5.dp))

            WeatherStateImage(imageUrl)

            Surface(modifier = Modifier.padding(0.dp), shape = CircleShape, color = Color(0xffffc400)) {
                Text(text = weather.weather[0].description, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }

            // Annotated String
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )
                ) {
                    append(formatDecimals(weather.main.temp_max) + "°")
                }
                withStyle(style = SpanStyle(
                    color = Color.LightGray
                )
                ) {
                    append(formatDecimals(weather.main.temp_min ) + "°")
                }
            })
        }
    }
}