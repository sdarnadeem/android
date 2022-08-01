package com.nasyxnadeem.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.nasyxnadeem.movieapp.model.Movie
import com.nasyxnadeem.movieapp.model.getMovies
import com.nasyxnadeem.movieapp.widgets.MovieItem

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    MovieInformation(movieId, navController)
}

@Composable
private fun MovieInformation(
    movieId: String?,
    navController: NavController
) {
    val movie = getMovies().filter {
        it.id == movieId
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray,
                elevation = 5.dp
            ) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(100.dp))
                    Text(text = "Movie")
                }
            }
        }
    ) {
        DetailsContent(movie[0])
    }

}

@Composable
private fun DetailsContent(
    movie: Movie,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MovieItem(movie = movie)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            Text(text = "Movie Images")

            ImageRow(movie)
        }


    }
}

@Composable
private fun ImageRow(movie: Movie) {
    LazyRow {
        items(items = movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "movie poster"
                )
            }
        }
    }
}