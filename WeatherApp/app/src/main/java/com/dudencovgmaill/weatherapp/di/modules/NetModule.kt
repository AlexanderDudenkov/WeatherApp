package com.dudencovgmaill.weatherapp.di.modules

import com.dudencovgmaill.weatherapp.BuildConfig
import com.dudencovgmaill.weatherapp.di.scopes.ApplicationScope
import com.dudencovgmaill.weatherapp.repo.remote.IApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): IApiService = retrofit.create(IApiService::class.java)

    @Provides
    @ApplicationScope
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    @ApplicationScope
    fun provideGson(builder: GsonBuilder): Gson = builder.setLenient().create()

    @Provides
    @ApplicationScope
    fun provideGsonBuilder(): GsonBuilder = GsonBuilder()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().apply {
                retryOnConnectionFailure(true)
                networkInterceptors().add(interceptor)
            }.build()

    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
}