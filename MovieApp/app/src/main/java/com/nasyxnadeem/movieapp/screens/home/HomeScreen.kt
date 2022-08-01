package com.nasyxnadeem.movieapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nasyxnadeem.movieapp.model.Movie
import com.nasyxnadeem.movieapp.model.getMovies
import com.nasyxnadeem.movieapp.navigation.MovieScreens
import com.nasyxnadeem.movieapp.widgets.MovieItem

@Composable
fun HomeScreen(navController: NavController) {


    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Gray, elevation = 5.dp) {
            Text(
                text = "Movies",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn {
            items(movieList) { movie ->
                MovieItem(movie) {
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/${movie.id}")
                }
            }
        }
    }
}

