package com.nasyxnadeem.capstoneapp.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasyxnadeem.capstoneapp.data.Resource
import com.nasyxnadeem.capstoneapp.model.book.Item
import com.nasyxnadeem.capstoneapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: BookRepository) : ViewModel() {

    var list : List<Item> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        searchBooks("android")
    }

     fun searchBooks(s: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (s.isEmpty()) {
                return@launch
            }

            try {
                when(val response = repo.getBooks(s)) {
                    is Resource.Success -> {
                        list = response.data!!
                        if (list.isNotEmpty()) {
                            isLoading = false
                        }
                    }
                    is Resource.Error -> {
                        isLoading = false
                        println("Error Message")
                    }
                    else -> {}
                }
            } catch (e: Exception) {
                isLoading = false
                println("Error" + e.message.toString())
            }
        }
    }
}