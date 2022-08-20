package com.nasyxnadeem.weatherforecast.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nasyxnadeem.weatherforecast.model.WeatherItem

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