package com.dudencovgmaill.weatherapp.di.modules

import com.dudencovgmaill.weatherapp.App
import com.dudencovgmaill.weatherapp.di.scopes.ApplicationScope
import com.dudencovgmaill.weatherapp.repo.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @ApplicationScope
    fun provideDb(): AppDatabase = App.getDb()
}