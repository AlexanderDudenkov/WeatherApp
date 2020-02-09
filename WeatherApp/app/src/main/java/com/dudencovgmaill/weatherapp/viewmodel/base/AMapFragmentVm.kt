package com.dudencovgmaill.weatherapp.viewmodel.base

import androidx.lifecycle.LiveData
import com.dudencovgmaill.weatherapp.repo.IRepository
import com.dudencovgmaill.weatherapp.repo.local.models.City

abstract class AMapFragmentVm(repo:IRepository) :ABaseVm(repo){
    abstract val city:LiveData<City>

    abstract fun setCityId(id:Long)
}