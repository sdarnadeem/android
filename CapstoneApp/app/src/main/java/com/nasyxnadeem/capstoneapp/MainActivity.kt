package com.nasyxnadeem.capstoneapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.firestore.FirebaseFirestore
import com.nasyxnadeem.capstoneapp.ui.theme.CapstoneAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp() {
                Text("Hello")
                val db = FirebaseFirestore.getInstance()
                val user: MutableMap<String, Any> = HashMap()

                user["firstName"] = "Jeo"
                user["lastName"] = "James"

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        Log.d("TAG", "onCreate: $it")
                    }
                    .addOnFailureListener {
                        Log.d("TAG", "onCreate: $it")
                    }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit = {}) {
    CapstoneAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}


