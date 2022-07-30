package com.nasyxnadeem.noteapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasyxnadeem.noteapp.model.Note
import com.nasyxnadeem.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
//    private var noteList = mutableStateListOf<Note>()

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())

    val noteList = _noteList.asStateFlow()
    init {
        //noteList.addAll(NoteData().loadNotes())

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect {
                    if (it.isNullOrEmpty()) {
                        Log.d("empty", "empty list: ")
                    } else {
                        _noteList.value = it
                    }
                }
        }
    }

     fun addNote(note: Note)  = viewModelScope.launch {
        repository.addNote(note)
    }

     fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

     fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }


}