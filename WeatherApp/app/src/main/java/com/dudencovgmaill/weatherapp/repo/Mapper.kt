package com.dudencovgmaill.weatherapp.repo

import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.dudencovgmaill.weatherapp.repo.remote.models.Response
import com.dudencovgmaill.weatherapp.tools.getDate

fun Response?.toCity(): City? = if (this != null) City().also {
    it.name = name ?: ""
    it.date = getDate()
    it.latitude = (coord?.lat ?: 0f).toString()
    it.longitude = (coord?.lon ?: 0f).toString()
    it.temperature = (main?.temp ?: 0f).toString()
} else null