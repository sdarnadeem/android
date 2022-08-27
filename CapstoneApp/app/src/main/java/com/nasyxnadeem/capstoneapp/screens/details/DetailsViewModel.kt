package com.nasyxnadeem.capstoneapp.screens.details

import androidx.lifecycle.ViewModel
import com.nasyxnadeem.capstoneapp.data.Resource
import com.nasyxnadeem.capstoneapp.model.book.Item
import com.nasyxnadeem.capstoneapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: BookRepository) : ViewModel() {

    suspend fun getBookInfo(bookId: String) : Resource<Item> {
        return repo.getBookInfo(bookId)
    }

}