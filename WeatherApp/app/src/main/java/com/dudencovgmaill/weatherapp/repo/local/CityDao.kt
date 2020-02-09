package com.dudencovgmaill.weatherapp.repo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dudencovgmaill.weatherapp.repo.local.models.City
import io.reactivex.Single

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModel(model: City): Long

    @Query("SELECT * FROM city")
    fun getAll(): Single<List<City>>

    @Query("SELECT * FROM city WHERE id = :id")
    fun getCity(id:Long): Single<City>
}
