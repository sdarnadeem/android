package com.nasyxnadeem.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nasyxnadeem.weatherforecast.screens.about.AboutScreen
import com.nasyxnadeem.weatherforecast.screens.favourite.FavouriteScreen
import com.nasyxnadeem.weatherforecast.screens.main.MainScreen
import com.nasyxnadeem.weatherforecast.screens.main.MainViewModel
import com.nasyxnadeem.weatherforecast.screens.search.SearchScreen
import com.nasyxnadeem.weatherforecast.screens.setting.SettingScreen
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
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}", arguments = listOf(
            navArgument(name = "city") {
                type = NavType.StringType
            }
        )) {
            it.arguments?.getString("city").let {city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, viewModel = mainViewModel, city = city)

            }

        }

        // Search Screen
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        // About Screen
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        // favourites screen
        composable(WeatherScreens.FavouriteScreen.name) {
            FavouriteScreen(navController = navController)
        }

        // Setting Screen
        composable(WeatherScreens.SettingScreen.name) {
            SettingScreen(navController = navController)
        }
    }


}