package com.nasyxnadeem.weatherforecast.data

import androidx.room.*
import com.nasyxnadeem.weatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM fav_tbl")
     fun getFavorites() : Flow<List<Favorite>>

    @Query("SELECT * FROM fav_tbl WHERE city=:city")
    suspend fun getFavById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

}