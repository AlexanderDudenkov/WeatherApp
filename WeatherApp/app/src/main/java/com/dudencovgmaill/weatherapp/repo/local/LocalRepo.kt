package com.dudencovgmaill.weatherapp.repo.local

import javax.inject.Inject

class LocalRepo : ILocalRepo {

    @Inject
    override lateinit var db: AppDatabase

}