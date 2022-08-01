package com.nasyxnadeem.noteapp.data

import androidx.room.*
import com.nasyxnadeem.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM notes")
    fun getNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}
