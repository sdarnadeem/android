package com.nasyxnadeem.weatherforecast.repository

import com.nasyxnadeem.weatherforecast.data.WeatherDao
import com.nasyxnadeem.weatherforecast.model.City
import com.nasyxnadeem.weatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// Mirroring weather dao
class WeatherDbRepository @Inject constructor(private val dao: WeatherDao) {

    fun getFavorites() : Flow<List<Favorite>> = dao.getFavorites()

    suspend fun insertFavorite(favorite: Favorite) = dao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = dao.updateFavorite(favorite)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun deleteFavorite(favorite: Favorite) = dao.deleteFavorite(favorite = favorite)

    suspend fun getFavoriteById(city: String): Favorite = dao.getFavById(city)

}