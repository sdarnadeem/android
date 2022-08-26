package com.nasyxnadeem.capstoneapp.screens.search

import InputField
import ReaderAppBar
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nasyxnadeem.capstoneapp.model.MBook
import com.nasyxnadeem.capstoneapp.navigation.ReaderScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun SearchScreen(navController: NavController = NavController(LocalContext.current), viewModel: SearchViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Search Books",
                icon = Icons.Default.ArrowBack,
                navController = navController,
                showProfile = false
            ) {
                navController.navigate(ReaderScreens.ReaderHomeScreen.name)
            }
        }
    ) {
        Column {
            SearchForm(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                searchQuery ->
                viewModel.searchBooks(searchQuery)
            }

            Spacer(modifier = Modifier.height(13.dp))

            BookList(navController = navController)
        }
    }
}

@Composable
fun BookList(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {
    val listOfBooks = viewModel.list

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
        items(items = listOfBooks) { book ->
            BookRow(book, navController)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}
) {
    Column {
        val searchQueryState = rememberSaveable() {
            mutableStateOf("")
        }

        val keyboardController = LocalSoftwareKeyboardController.current

        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }

        InputField(
            valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions

                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })
    }
}


@Composable

fun BookRow(book: MBook, navController: NavController) {
    Card(
        modifier = Modifier.clickable { }.fillMaxWidth().height(100.dp).padding(3.dp),
        shape = RectangleShape,
        elevation = 7.dp
    ) {

        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.Top) {

            val imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMvHFhNnMfyhZrz8vkkVA1XkaJojTp7OUw4QYA4enWgg&s"

            Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = null, modifier = Modifier.width(80.dp).fillMaxHeight().padding(end = 4.dp))

            Column {
                Text(text = book.title.toString(), overflow = TextOverflow.Ellipsis )

                Text(text = "Author: " + book.authors.toString(), overflow = TextOverflow.Clip, style = MaterialTheme.typography.caption )


            }
        }
    }
}