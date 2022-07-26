package com.nasyxnadeem.noteapp.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String,
    enabled: Boolean = true


) {
    Button(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}