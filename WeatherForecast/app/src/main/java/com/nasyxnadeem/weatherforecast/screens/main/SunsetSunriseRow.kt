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
import com.nasyxnadeem.weatherforecast.model.City
import com.nasyxnadeem.weatherforecast.utils.formatDateTime

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