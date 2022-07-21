package com.nasyxnadeem.bizcard01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nasyxnadeem.bizcard01.ui.theme.Bizcard01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bizcard01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {
          Column(modifier = Modifier.height(300.dp), verticalArrangement = Arrangement.Top, Alignment.CenterHorizontally) {
              CreateProfileImage()
          }
        }
    }
}

@Composable
private fun CreateProfileImage() {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.nadeem), contentDescription = "profile image",
            modifier = Modifier.size(135.dp), contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CreateBizCard()
}