package com.nasyxnadeem.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nasyxnadeem.noteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDatabaseDao
}