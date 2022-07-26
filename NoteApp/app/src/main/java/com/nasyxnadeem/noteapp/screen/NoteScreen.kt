package com.nasyxnadeem.noteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nasyxnadeem.noteapp.R
import com.nasyxnadeem.noteapp.components.NoteInputText

@Composable
fun NoteScreen() {

    var title by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }
    Column(

    ) {
        TopBar()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), text = title, label = "Title", onTextChange = {
                title = it
            })

            NoteInputText(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),text = content, label = "Content", onTextChange = {
                content = it
            })
        }
    }
}

@Composable
private fun TopBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    }, actions = {
        Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "icon")
    }, backgroundColor = Color(0xffdadfe3))
}

@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    NoteScreen()
}