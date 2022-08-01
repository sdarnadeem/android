package com.nasyxnadeem.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.*

@Entity(tableName = "notes")
data class Note(

    @PrimaryKey
    val id : UUID = UUID.randomUUID(),

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,

//    val entryDate: LocalDateTime = LocalDateTime.from(Instant.now())
)
