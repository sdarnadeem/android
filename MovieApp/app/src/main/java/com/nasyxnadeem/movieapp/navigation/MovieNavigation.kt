package com.nasyxnadeem.movieapp.navigation

import com.nasyxnadeem.movieapp.screens.details.DetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nasyxnadeem.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController)
        }

        // www.google.com/detail-screen/id=34
//        composable(MovieScreens.DetailsScreen.name+"/{movie}",
//        arguments = listOf(navArgument(name = "movie") {type = androidx.navigation.NavType.StringType})) {
//            DetailsScreen(navController = navController, it.arguments?.getString("movie"))
//        }
    }
}