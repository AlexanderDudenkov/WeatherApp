package com.dudencovgmaill.weatherapp.repo.remote.models

data class Response(
    var coord: Coord?,
    var main: Main?,
    var name: String?
)

data class Coord(
    var lat: Float?,
    var lon: Float?
)

data class Main(
    var humidity: Int?,
    var pressure: Float?,
    var temp: Float?,
    var tempMax: Float?,
    var tempMin: Float?
)