package com.dudencovgmaill.weatherapp.repo.remote

import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.dudencovgmaill.weatherapp.repo.toCity
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepo : IRemoteRepo {
    @Inject
    override lateinit var apiService: IApiService

    override fun getResponse(city: String): Single<City?> =
        apiService.getResponse(city).flatMap { Single.just(it.toCity()) }

    override fun getResponse(lat: String, lon: String): Single<City?> =
        apiService.getResponse(lat = lat, lon = lon).flatMap { Single.just(it.toCity()) }
}