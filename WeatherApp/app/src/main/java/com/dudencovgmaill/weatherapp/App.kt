package com.dudencovgmaill.weatherapp

import android.app.Application
import androidx.room.RoomDatabase
import com.dudencovgmaill.weatherapp.di.IDaggerComponentProvider
import com.dudencovgmaill.weatherapp.di.components.DaggerIAppComponent
import com.dudencovgmaill.weatherapp.di.components.IAppComponent
import com.dudencovgmaill.weatherapp.repo.local.AppDatabase
import timber.log.Timber

class App : Application(), IDaggerComponentProvider {

    override val component: IAppComponent by lazy {
        DaggerIAppComponent.builder().applicationContext(applicationContext).build()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        initTimberkt()

        db = AppDatabase.getAppDataBase(this)!!
    }

    private fun initTimberkt() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        private lateinit var instance: App
        private lateinit var db: AppDatabase

        fun getInstance() = instance
        fun getDb() = db
    }
}