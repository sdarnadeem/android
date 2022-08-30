package com.nasyxnadeem.capstoneapp.model.book

data class Book(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)