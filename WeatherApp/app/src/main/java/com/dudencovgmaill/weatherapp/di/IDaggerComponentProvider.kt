package com.dudencovgmaill.weatherapp.di

import android.app.Activity
import com.dudencovgmaill.weatherapp.di.components.IAppComponent

interface IDaggerComponentProvider {
    val component: IAppComponent
}

val Activity.injector get() = (application as IDaggerComponentProvider).component