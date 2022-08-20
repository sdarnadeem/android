package com.nasyxnadeem.capstoneapp.navigation

enum class ReaderScreens {
    SplashScreen, LoginScreen, CreateAccountScreen, ReaderHomeScreen, SearchScreen, DetailScreen, UpdateScreen, ReaderStatsScreen;

    companion object {
        fun fromRoute(route: String?): ReaderScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                CreateAccountScreen.name -> CreateAccountScreen
                SearchScreen.name -> SearchScreen
                DetailScreen.name -> DetailScreen
                ReaderHomeScreen.name -> ReaderHomeScreen
                UpdateScreen.name -> UpdateScreen
                ReaderStatsScreen.name -> ReaderStatsScreen
                null -> ReaderHomeScreen
                else -> throw IllegalArgumentException("Route $route is not defined")
            }
    }
}