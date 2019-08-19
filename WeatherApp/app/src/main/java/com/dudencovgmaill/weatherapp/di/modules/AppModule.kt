package com.dudencovgmaill.weatherapp.di.modules

import com.dudencovgmaill.weatherapp.di.scopes.ApplicationScope
import com.dudencovgmaill.weatherapp.repo.IRepository
import com.dudencovgmaill.weatherapp.repo.Repository
import com.dudencovgmaill.weatherapp.repo.local.AppDatabase
import com.dudencovgmaill.weatherapp.repo.local.ILocalRepo
import com.dudencovgmaill.weatherapp.repo.local.LocalRepo
import com.dudencovgmaill.weatherapp.repo.remote.IApiService
import com.dudencovgmaill.weatherapp.repo.remote.IRemoteRepo
import com.dudencovgmaill.weatherapp.repo.remote.RemoteRepo
import dagger.Module
import dagger.Provides

@Module(includes = [NetModule::class, DbModule::class])
class AppModule {

    @Provides
    @ApplicationScope
    fun provideIRepository(local: ILocalRepo, remote: IRemoteRepo): IRepository =
            Repository().apply {
                localRepo = local
                remoteRepo = remote
            }

    @Provides
    @ApplicationScope
    fun provideILocalRepo(db: AppDatabase): ILocalRepo = LocalRepo().apply { this.db = db }

    @Provides
    @ApplicationScope
    fun provideIRemoteRepo(apiService: IApiService): IRemoteRepo = RemoteRepo().apply {
        this.apiService = apiService
    }
}