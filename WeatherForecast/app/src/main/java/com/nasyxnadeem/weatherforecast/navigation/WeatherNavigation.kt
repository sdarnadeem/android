package com.nasyxnadeem.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nasyxnadeem.weatherforecast.screens.main.MainScreen
import com.nasyxnadeem.weatherforecast.screens.main.MainViewModel
import com.nasyxnadeem.weatherforecast.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {

    // creating a controller
    val navController = rememberNavController()

    // creating a nav host
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {

        // creating all the screens

        // Splash Screen
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        // Main Screen
        composable(WeatherScreens.MainScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, viewModel = mainViewModel)
        }
    }


}