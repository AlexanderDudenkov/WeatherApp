package com.dudencovgmaill.weatherapp.repo

import com.dudencovgmaill.weatherapp.repo.local.ILocalRepo
import com.dudencovgmaill.weatherapp.repo.remote.IRemoteRepo

interface IRepository {
    val localRepo: ILocalRepo
    val remoteRepo: IRemoteRepo
}
