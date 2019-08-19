package com.dudencovgmaill.weatherapp.di.components

import com.dudencovgmaill.weatherapp.di.modules.MainFragmentModule
import com.dudencovgmaill.weatherapp.di.scopes.FragmentScope
import com.dudencovgmaill.weatherapp.view.MainFragment
import dagger.Component

@FragmentScope
@Component(modules = [MainFragmentModule::class], dependencies = [IAppComponent::class])
interface IMainFragmentComponent {
    fun inject(mainFragment: MainFragment)
}