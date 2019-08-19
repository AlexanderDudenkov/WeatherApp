package com.dudencovgmaill.weatherapp.repo

import com.dudencovgmaill.weatherapp.repo.local.ILocalRepo
import com.dudencovgmaill.weatherapp.repo.remote.IRemoteRepo
import javax.inject.Inject

class Repository : IRepository {
    @Inject
    override lateinit var remoteRepo: IRemoteRepo
    @Inject
    override lateinit var localRepo: ILocalRepo
}
