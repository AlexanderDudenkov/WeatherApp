package com.dudencovgmaill.weatherapp.repo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dudencovgmaill.weatherapp.repo.local.models.City

@Database(
    entities = [City::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}