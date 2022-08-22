package com.nasyxnadeem.capstoneapp.screens.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController) {
    Scaffold(
        topBar = {},
        floatingActionButton = { FABContent() {

        } }
    ) {
        val iti = it
    }
}

@Composable
private fun FABContent(
    onTap: (String) -> Unit = {}
){
    FloatingActionButton(
        onClick = {onTap},
        shape = RoundedCornerShape(50.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add a book", tint = MaterialTheme.colors.onSecondary)
    }
}