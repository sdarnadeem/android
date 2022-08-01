package com.nasyxnadeem.noteapp.data

import com.nasyxnadeem.noteapp.model.Note

class NoteData {
     fun loadNotes(): List<Note> {
        return listOf(

            Note(title = "A good day", content = "We went on a vacation by the lake"),
            Note(title = "Android Compose", content = "Working on Android Compose course today"),
            Note(title = "Keep at it...", content = "Sometimes things just happen"),
            Note(title = "A movie day", content = "Watching a movie with family today"),
            Note(title = "A movie day", content = "Watching a movie with family today"),
            Note(title = "A movie day", content = "Watching a movie with family today"),
            Note(title = "A movie day", content = "Watching a movie with family today"),
            Note(title = "A movie day", content = "Watching a movie with family today"),
            Note(title = "A movie day", content = "Watching a movie with family today"),
            Note(title = "A movie day", content = "Watching a movie with family")

        )
    }
}