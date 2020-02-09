package com.dudencovgmaill.weatherapp.di.components

import android.content.Context
import com.dudencovgmaill.weatherapp.di.scopes.ApplicationScope
import com.dudencovgmaill.weatherapp.di.modules.AppModule
import com.dudencovgmaill.weatherapp.viewmodel.MainFragmentVm
import com.dudencovgmaill.weatherapp.viewmodel.MapFragmentVm
import com.dudencovgmaill.weatherapp.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface IAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): IAppComponent
    }

    fun mainViewModelFactory(): ViewModelFactory<MainFragmentVm>
    fun mapViewModelFactory(): ViewModelFactory<MapFragmentVm>
}