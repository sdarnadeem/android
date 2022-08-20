package com.nasyxnadeem.capstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nasyxnadeem.capstoneapp.navigation.ReaderNavigation
import com.nasyxnadeem.capstoneapp.ui.theme.CapstoneAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderApp()


        }
    }
}


@Composable
fun ReaderApp() {
    CapstoneAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReaderNavigation()
            }
        }
    }
}

//                Text("Hello")
//                val db = FirebaseFirestore.getInstance()
//                val user: MutableMap<String, Any> = HashMap()
//
//                user["firstName"] = "Jeo"
//                user["lastName"] = "James"
//
//                db.collection("users")
//                    .add(user)
//                    .addOnSuccessListener {
//                        Log.d("TAG", "onCreate: $it")
//                    }
//                    .addOnFailureListener {
//                        Log.d("TAG", "onCreate: $it")
//                    }
//


