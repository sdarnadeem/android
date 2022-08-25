package com.nasyxnadeem.capstoneapp.navigation

import SearchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nasyxnadeem.capstoneapp.screens.SplashScreen
import com.nasyxnadeem.capstoneapp.screens.home.Home
import com.nasyxnadeem.capstoneapp.screens.login.LoginScreen
import com.nasyxnadeem.capstoneapp.screens.stats.StatsScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name ) {

        composable(route = ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = ReaderScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(route = ReaderScreens.ReaderHomeScreen.name) {
            Home(navController = navController)
        }

        composable(route = ReaderScreens.ReaderStatsScreen.name) {
            StatsScreen(navController = navController)
        }
        composable(route = ReaderScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}