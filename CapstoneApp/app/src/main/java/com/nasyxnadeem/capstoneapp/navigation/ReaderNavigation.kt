package com.nasyxnadeem.capstoneapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nasyxnadeem.capstoneapp.screens.SplashScreen
import com.nasyxnadeem.capstoneapp.screens.details.BookDetailsScreen
import com.nasyxnadeem.capstoneapp.screens.home.Home
import com.nasyxnadeem.capstoneapp.screens.home.HomeScreenViewModel
import com.nasyxnadeem.capstoneapp.screens.login.LoginScreen
import com.nasyxnadeem.capstoneapp.screens.search.SearchScreen
import com.nasyxnadeem.capstoneapp.screens.search.SearchViewModel
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
            val viewModel = hiltViewModel<HomeScreenViewModel>()
            Home(navController = navController, viewModel = viewModel)
        }

        composable(route = ReaderScreens.ReaderStatsScreen.name) {
            StatsScreen(navController = navController)
        }
        composable(route = ReaderScreens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(navController = navController, viewModel = searchViewModel)
        }

        val detailName = ReaderScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId") {
            type = NavType.StringType
        })) {
            it.arguments?.getString("bookId").let {bookId ->
                BookDetailsScreen(navController = navController, bookId = bookId.toString())
            }


        }
    }
}