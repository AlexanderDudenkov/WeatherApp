package com.dudencovgmaill.weatherapp.repo.local

import com.dudencovgmaill.weatherapp.repo.local.models.City
import io.reactivex.Single
import javax.inject.Inject

class LocalRepo : ILocalRepo {

    @Inject
    override lateinit var db: AppDatabase

    override fun insertModel(model: City): Long = db.cityDao().insertModel(model)

    override fun getAll(): Single<List<City>> = db.cityDao().getAll()

    override fun getCity(id: Long): Single<City> = db.cityDao().getCity(id)
}