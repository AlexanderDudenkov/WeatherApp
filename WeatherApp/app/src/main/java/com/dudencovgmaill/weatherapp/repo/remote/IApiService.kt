package com.dudencovgmaill.weatherapp.repo.remote

import com.dudencovgmaill.weatherapp.repo.remote.models.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("weather")
    fun getResponse(@Query("q") city: String,
                    @Query("units") unit: String="metric",
                    @Query("appid") apiKey: String="e3ffa2efac651bbee00fa300bfe5b550"): Single<Response?>

    @GET("weather")
    fun getResponse(@Query("lat") lat: String,
                    @Query("lon") lon: String,
                    @Query("units") unit: String="metric",
                    @Query("appid") apiKey: String="e3ffa2efac651bbee00fa300bfe5b550"): Single<Response?>
}
