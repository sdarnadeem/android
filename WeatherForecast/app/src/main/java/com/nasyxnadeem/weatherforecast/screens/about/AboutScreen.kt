package com.nasyxnadeem.weatherforecast.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.nasyxnadeem.weatherforecast.R
import com.nasyxnadeem.weatherforecast.widgets.WeatherTopBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherTopBar(title = "About", icon = Icons.Default.ArrowBack, isMainScreen = false, navController = navController) {
                navController.popBackStack()
            }
        }
    ) {
        val sc = it
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.about_app), style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)

                Text(text = stringResource(R.string.api_used), style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Light)


                Text(text = "Developed by Nasyx Nadeem", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Light)
            }
        }
    }
}