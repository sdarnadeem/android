package com.nasyxnadeem.capstoneapp.screens.home

import FABContent
import ListCard
import ReaderAppBar
import TitleSection
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.nasyxnadeem.capstoneapp.model.MBook
import com.nasyxnadeem.capstoneapp.navigation.ReaderScreens

@Composable
fun Home(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { ReaderAppBar(title = "Nasyx Nadeem", navController = navController) },
        floatingActionButton = {
            FABContent {
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        }
    ) {
        val iti = it
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController, viewModel)
        }
    }
}

@Composable
fun HomeContent(navController: NavController, viewModel: HomeScreenViewModel) {

    var listOfBooks = emptyList<MBook>()
    val currentUser = FirebaseAuth.getInstance().currentUser

    if (!viewModel.data.value.data.isNullOrEmpty()) {
        listOfBooks = viewModel.data.value.data!!.toList().filter {
            it.userId == currentUser?.uid
        }


    }

    val currentUserName = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
    } else {
        "N/A"
    }

    Column(
        modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.align(alignment = Alignment.Start)
        ) {
            TitleSection(label = "Your reading\nActivity right now")

            Spacer(modifier = Modifier.fillMaxWidth(0.5f))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.clickable {
                        // TODO ; GO TO CONTROLLER
                        navController.navigate(ReaderScreens.ReaderStatsScreen.name )
                    }.size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )

                Text(
                    text = currentUserName.toString(),
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    fontSize  = 15.sp,
                    maxLines = 1,
                    color = Color.Red,
                    overflow = TextOverflow.Clip
                )

                Divider()
            }
        }
        ReadingRightNowArea(books = listOf(), navController = navController)

        TitleSection(label = "Reading  List")

        BookListArea(books = listOfBooks, navController = navController)
    }
}

@Composable
fun BookListArea(books: List<MBook>, navController: NavController) {

    val addedBooks = books.filter {
        book ->
        book.startReading == null && book.finishedReading == null
    }
    HorizontalScrollableComponent(addedBooks) {
        navController.navigate(ReaderScreens.UpdateScreen.name + "/$it")
    }
}

@Composable
fun HorizontalScrollableComponent(books: List<MBook>, viewModel: HomeScreenViewModel = hiltViewModel(), onCardPressed: (String) -> Unit = {}) {

    val scrollState = rememberScrollState()

    Row(modifier = Modifier.fillMaxWidth().heightIn(280.dp).horizontalScroll(scrollState)) {

        if (viewModel.data.value.loading == true) {
            LinearProgressIndicator()
        } else {
            if (books.isNullOrEmpty()) {
                Surface(modifier = Modifier.padding(23.dp)) {
                    Text("No books found, please add a book")
                }
            }
        }
        for (book in books) {
            ListCard(book) {
                onCardPressed(book.googleBookId.toString())
            }
        }
    }
}


@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController) {

    val readingNowList = books.filter {
        book ->
        book.startReading == null && book.finishedReading == null
    }
    HorizontalScrollableComponent(readingNowList) {
        navController.navigate(ReaderScreens.UpdateScreen.name +"/$it")
    }
}



