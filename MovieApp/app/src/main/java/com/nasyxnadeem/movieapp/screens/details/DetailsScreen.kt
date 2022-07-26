package com.nasyxnadeem.movieapp.screens.details
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nasyxnadeem.movieapp.model.Movie

@Composable
fun DetailsScreen(navController: NavController, movieData: Movie) {
    MovieInformation(movieData, navController)
}

@Composable
private fun MovieInformation(
    movieData: Movie,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray,
                elevation = 5.dp
            ) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", modifier = Modifier.clickable{
                        navController.popBackStack()
                    })
                    Spacer(modifier = Modifier.width(100.dp))
                    Text(text = "Movie")
                }
            }
        }
    ) {
        DetailsContent(movieData)
    }

}

@Composable
private fun DetailsContent(
    movieData: Movie,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = movieData.title, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(23.dp))

        }


    }
}