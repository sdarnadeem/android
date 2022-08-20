package com.nasyxnadeem.capstoneapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nasyxnadeem.capstoneapp.screens.home.Home
import com.nasyxnadeem.capstoneapp.screens.SplashScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name ) {

        composable(route = ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = ReaderScreens.ReaderHomeScreen.name) {
            Home(navController = navController)
        }
    }
}