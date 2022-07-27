package com.nasyxnadeem.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nasyxnadeem.noteapp.screen.NoteScreen
import com.nasyxnadeem.noteapp.screen.NoteViewModel
import com.nasyxnadeem.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)

                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    NoteScreen(
        notes = noteViewModel.getAllNotes(),
        onRemoveNote = {
            noteViewModel.removeNote(it)
        },
        onAddNote = {
            noteViewModel.addNote(it)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {
    }
}