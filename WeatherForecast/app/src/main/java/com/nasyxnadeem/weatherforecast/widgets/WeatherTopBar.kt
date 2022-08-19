package com.nasyxnadeem.weatherforecast.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nasyxnadeem.weatherforecast.model.Favorite
import com.nasyxnadeem.weatherforecast.navigation.WeatherScreens
import com.nasyxnadeem.weatherforecast.screens.favourite.FavoriteViewModel

@Composable
fun WeatherTopBar(
    title: String = "Srinagar",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }
    val showIt = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }


    TopAppBar(

        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
            )
        },

        actions = {
            if (isMainScreen) {


                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
                }

                IconButton(onClick = { showDialog.value = true }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "more icon")
                }

            } else Box {}
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })

            }
            val isAlreadyFavList = favoriteViewModel.favList.collectAsState().value.filter {
                    item -> (item.city == title.split(",")[0])
            }
            if (isMainScreen) {

                val fav = Favorite(
                    city = title.split(",")[0],
                    country = title.split(",")[1]
                )
                Icon(
                    imageVector = SetIcon(isAlreadyFavList),
                    contentDescription = null,
                    modifier = Modifier.scale(0.9f).clickable {
                        if (isAlreadyFavList.isEmpty()) {
                            favoriteViewModel.insertFavorite(
                                fav
                            ).run {
                                showIt.value = true
                            }
                        } else {
                            favoriteViewModel.deleteFavorite(fav).run {
                                showIt.value = true
                            }
                        }
                    },
                    tint = Color.Red
                )
            }
            ShowToast(context = context, showIt = showIt, isAlreadyFav = isAlreadyFavList )
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}
@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>, isAlreadyFav: List<Favorite>) {
    if (showIt.value && isAlreadyFav.isEmpty()) {
        Toast.makeText(context, "City Added to Favorites", Toast.LENGTH_SHORT).show()
    }

    if (showIt.value && isAlreadyFav.isNotEmpty()) {
        Toast.makeText(context, "City removed from Favorites", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {

    var expanded by remember {
        mutableStateOf(true)
    }

    val items = listOf("About", "Favorites", "Setting")

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (text) {
                            "About" -> Icons.Default.Info
                            "Favorites" -> Icons.Default.Favorite
                            else -> Icons.Default.Settings
                        }, contentDescription = null, tint = Color.LightGray
                    )
                    Text(text = text, fontWeight = FontWeight.W300, modifier = Modifier.clickable {
                        navController.navigate(
                            when (text) {
                                "About" -> WeatherScreens.AboutScreen.name
                                "Favorites" -> WeatherScreens.FavouriteScreen.name
                                else -> WeatherScreens.SettingScreen.name
                            }
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun SetIcon(isAlreadyFav: List<Favorite>) : ImageVector {
    return if (isAlreadyFav.isNotEmpty()) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }
}
