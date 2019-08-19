package com.dudencovgmaill.weatherapp.di.modules

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudencovgmaill.weatherapp.di.scopes.FragmentScope
import com.dudencovgmaill.weatherapp.view.MainActivity
import com.dudencovgmaill.weatherapp.view.adapters.MainAdapter
import com.dudencovgmaill.weatherapp.view.adapters.MainViewHolder
import dagger.Module
import dagger.Provides

@Module
class MainFragmentModule(private val activity: MainActivity) {

    @Provides
    @FragmentScope
    fun provideFragmentModule(): Context = activity.baseContext

    @Provides
    @FragmentScope
    fun provideLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(provideFragmentModule())

    @Provides
    @FragmentScope
    fun provideGalleryAdapter(): RecyclerView.Adapter<MainViewHolder> = MainAdapter()
}