package com.nasyxnadeem.noteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nasyxnadeem.noteapp.data.NoteData
import com.nasyxnadeem.noteapp.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteData().loadNotes())
    }

    fun addNote(note: Note)  {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes() : List<Note> {
        return noteList
    }
}