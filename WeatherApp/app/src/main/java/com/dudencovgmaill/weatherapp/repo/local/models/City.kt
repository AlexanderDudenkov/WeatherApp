package com.dudencovgmaill.weatherapp.repo.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class City(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "latitude")
    var latitude: String = "",
    @ColumnInfo(name = "longitude")
    var longitude: String = "",
    @ColumnInfo(name = "date")
    var date: String = "",
    @ColumnInfo(name = "temperature")
    var temperature: String = "",
    @ColumnInfo(name = "unit")
    var unit: String = "C deg."
)