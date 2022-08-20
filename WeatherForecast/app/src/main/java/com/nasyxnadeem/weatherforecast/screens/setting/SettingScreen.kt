package com.nasyxnadeem.weatherforecast.screens.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nasyxnadeem.weatherforecast.widgets.WeatherTopBar

@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingViewModel = hiltViewModel()) {
    var unitToggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf("Imperial (F)", "Metric (C)")
    var choiceState by remember { mutableStateOf("")}
    Scaffold(
        topBar = {
            WeatherTopBar(
                title = "Settings",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                navController = navController
            )
        }
    ) { it ->
        val did = it
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change units of measurement",
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                IconToggleButton(checked = unitToggleState,
                onCheckedChange = {
                    unitToggleState = !it
                    choiceState = if (unitToggleState) {
                        "Imperial (F)"
                    } else {
                        "Metric (C)"
                    }
                }
                    ) {

                }
            }
        }
    }
}