package com.nasyxnadeem.weatherforecast.screens.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nasyxnadeem.weatherforecast.model.Favorite
import com.nasyxnadeem.weatherforecast.navigation.WeatherScreens
import com.nasyxnadeem.weatherforecast.widgets.WeatherTopBar

@Composable
fun FavouriteScreen(navController: NavController, viewModel: FavoriteViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { WeatherTopBar(
            title = "Favorites",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController
        ) {
            navController.popBackStack()
        }}
    ) {
        val d = it
        
        Surface(
            modifier = Modifier.padding(5.dp).fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = viewModel.favList.collectAsState().value

                LazyColumn {
                    items(items = list) {
                        item ->
                        CityRow(favorite = item, navController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(favorite: Favorite, navController: NavController, viewModel: FavoriteViewModel) {

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                val route = WeatherScreens.MainScreen.name
                       navController.navigate("$route/${favorite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xffb2dfdb)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(start = 4.dp))

            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xffd1e3e1)
            ) {
                Text(text = favorite.country, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null, modifier = Modifier.clickable {
                                                                                                              viewModel.deleteFavorite(favorite)
            }, tint = Color.Red.copy(alpha = 0.3f))
        }
    }

}
