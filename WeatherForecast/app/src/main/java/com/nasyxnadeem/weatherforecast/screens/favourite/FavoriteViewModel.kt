package com.nasyxnadeem.weatherforecast.screens.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasyxnadeem.weatherforecast.model.Favorite
import com.nasyxnadeem.weatherforecast.repository.WeatherDbRepository
import com.nasyxnadeem.weatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repo : WeatherDbRepository) : ViewModel(){

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())

    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getFavorites().distinctUntilChanged()
                .collect {
                    lisOfFavs ->
                    if (lisOfFavs.isNullOrEmpty()) {
                        Log.d("data", "Empty favs")
                    } else {
                        _favList.value = lisOfFavs
                        Log.d("data", favList.value.toString())
                    }
                }
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
        repo.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
        repo.deleteFavorite(favorite)
    }
}