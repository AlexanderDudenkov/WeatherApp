package com.dudencovgmaill.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dudencovgmaill.weatherapp.repo.IRepository
import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.dudencovgmaill.weatherapp.viewmodel.base.AMapFragmentVm
import javax.inject.Inject

open class MapFragmentVm @Inject constructor(repo: IRepository) : AMapFragmentVm(repo) {

    override val city: LiveData<City> = MutableLiveData()

    override fun setCityId(id: Long) {
        if (id != 0L)
            getCity(id) { (city as MutableLiveData).value = it }
    }
}