package com.dudencovgmaill.weatherapp.repo.local

import com.dudencovgmaill.weatherapp.repo.local.models.City
import io.reactivex.Single

interface ILocalRepo {
    val db: AppDatabase

    fun insertModel(model: City): Long
    fun getAll(): Single<List<City>>
    fun getCity(id:Long):Single<City>
}