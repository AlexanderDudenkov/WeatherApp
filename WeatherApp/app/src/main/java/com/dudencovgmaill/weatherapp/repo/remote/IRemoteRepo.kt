package com.dudencovgmaill.weatherapp.repo.remote

import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.dudencovgmaill.weatherapp.repo.remote.models.Response
import com.dudencovgmaill.weatherapp.repo.toCity
import io.reactivex.Single

interface IRemoteRepo {
    var apiService: IApiService

    fun getResponse(city: String): Single<City?>
    fun getResponse(lat: String,lon:String): Single<City?>
}