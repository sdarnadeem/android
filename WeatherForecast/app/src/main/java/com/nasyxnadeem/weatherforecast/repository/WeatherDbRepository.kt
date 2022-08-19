package com.nasyxnadeem.weatherforecast.repository

import com.nasyxnadeem.weatherforecast.data.WeatherDao
import com.nasyxnadeem.weatherforecast.model.City
import com.nasyxnadeem.weatherforecast.model.Favorite
import com.nasyxnadeem.weatherforecast.model.Unit
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

    // Units

    fun getUnits(): Flow<List<Unit>> = dao.getUnits()

    suspend fun  insertUnit(unit: Unit) = dao.insertUnit(unit)

    suspend fun updateUnit(unit: Unit) = dao.updateUnit(unit)

    suspend fun deleteUnit(unit: Unit) = dao.deleteUnit(unit)


}