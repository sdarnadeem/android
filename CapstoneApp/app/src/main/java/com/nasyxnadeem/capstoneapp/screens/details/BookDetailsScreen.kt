package com.nasyxnadeem.capstoneapp.screens.details

import ReaderAppBar
import RoundedButton
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nasyxnadeem.capstoneapp.data.Resource
import com.nasyxnadeem.capstoneapp.model.MBook
import com.nasyxnadeem.capstoneapp.model.book.Item
import com.nasyxnadeem.capstoneapp.navigation.ReaderScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookDetailsScreen(
    navController: NavController,
    bookId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    Scaffold(topBar = {
        ReaderAppBar(
            title = "Book Details",
            icon = Icons.Default.ArrowBack,
            showProfile = false,
            navController = navController
        ) {
            navController.navigate(ReaderScreens.SearchScreen.name)
        }
    }) {
        Surface(modifier = Modifier.padding(3.dp).fillMaxSize()) {
            Column(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val bookInfo = produceState<Resource<Item>>(initialValue = Resource.Loading()) {
                    value = viewModel.getBookInfo(bookId)
                }.value

                if (bookInfo.data == null) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                } else {
                    ShowBookDetails(bookInfo, navController)

                }

            }
        }
    }

}

@Composable
fun ShowBookDetails(bookInfo: Resource<Item>, navController: NavController) {
    val bookData = bookInfo.data?.volumeInfo
    val googleBookid = bookInfo.data?.id

    Card(modifier = Modifier.padding(34.dp), shape = CircleShape, elevation = 4.dp) {
        Image(
            painter = rememberAsyncImagePainter(model = bookData?.imageLinks?.thumbnail.toString()),
            contentDescription = null,
            modifier = Modifier.width(90.dp).height(90.dp).padding(1.dp)
        )


    }

    Text(
        text = bookData?.title.toString(),
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        maxLines = 19
    )

    Text(
        text = "Authors: " + bookData?.authors.toString(),
    )
    Text(
        text = "Page Count: " + bookData?.pageCount.toString(),
    )
    Text(
        text = "Categories: " + bookData?.categories.toString(),
        style = MaterialTheme.typography.subtitle1
    )
    Text(
        text = "Published: " + bookData?.publishedDate.toString(),
        style = MaterialTheme.typography.subtitle1
    )

    Divider(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))

    val cleanDescription = HtmlCompat.fromHtml(
        bookData?.description.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY
    )

    val localDims = LocalContext.current.resources.displayMetrics
    Surface(
        modifier = Modifier.height(localDims.heightPixels.dp.times(0.09f)).padding(4.dp),
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        LazyColumn(modifier = Modifier.padding(3.dp)) {
            item {
                Text(text = cleanDescription.toString())
            }
        }
    }

    Row(modifier = Modifier.padding(top = 6.dp), horizontalArrangement = Arrangement.SpaceAround) {
        RoundedButton(label = "save") {
            val book = MBook(
                title = bookData?.title,
                authors = bookData?.authors.toString(),
                description = bookData?.description,
                categories = bookData?.categories.toString(),
                notes = "",
                photoUrl = bookData?.imageLinks?.thumbnail,
                publishedDate = bookData?.publishedDate,
                pageCount = bookData?.pageCount.toString(),
                rating = 0.0,
                googleBookId = googleBookid,
                userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
            )
//            SaveToFirebase(book, navController)
        }
        Spacer(modifier = Modifier.width(25.dp))

        RoundedButton(label = "Cancel") {
//            navController.popBackStack()
        }
    }
}

@Composable
fun SaveToFirebase(book: MBook, navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    val dbCollection = db.collection("books")

    if (book.toString().isNotEmpty()) {
        dbCollection.add(book).addOnSuccessListener {
            documentRef ->
            val docId = documentRef.id
            dbCollection.document(docId).update(
                hashMapOf("id" to docId) as Map<String, Any>
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    navController.popBackStack()
                }
            }.addOnFailureListener {
                println("firebase error")
            }
        }
    }

}
