package com.nasyxnadeem.noteapp.screen

import android.content.Context
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nasyxnadeem.noteapp.R
import com.nasyxnadeem.noteapp.components.CustomButton
import com.nasyxnadeem.noteapp.components.NoteInputText
import com.nasyxnadeem.noteapp.components.NoteItem
import com.nasyxnadeem.noteapp.model.Note

@Composable
fun NoteScreen(
    notes: List<Note> = emptyList(),
    onAddNote: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {},
) {

    var showForm by remember {
        mutableStateOf(false)
    }
    var title by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column {
        TopBar(onClick = {showForm = true})


        AnimatedVisibility(visible = showForm) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    title = it
                })

            NoteInputText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = content,
                label = "Content",
                onTextChange = {
                    content = it
                })

            CustomButton(modifier = Modifier.padding(top = 10.dp), text = "save", onClick = {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    onAddNote(Note(title = title, content = content))
                    title = ""
                    content = ""
                    showForm = false

                    Toast.makeText(context, "Note added Successfully", Toast.LENGTH_SHORT).show()
                }
            })

        }
            }
        
        LazyColumn {
            items(items = notes) {
                note ->

NoteItem(note = note, onNoteClicked = {

        onRemoveNote(note)

})            }
        }
    }
}

@Composable
private fun TopBar(
    onClick: () -> Unit
) {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    }, actions = {
        Icon(modifier = Modifier.padding(end = 20.dp).size(20.dp).clickable{ onClick()}, imageVector = Icons.Rounded.Add, contentDescription = "icon")
    }, backgroundColor = Color(0xffdadfe3))
}

@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    NoteScreen()
}