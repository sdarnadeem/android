package com.nasyxnadeem.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nasyxnadeem.weatherforecast.screens.SplashScreen

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
            MainScreen(navController = navController)
        }
    }


}