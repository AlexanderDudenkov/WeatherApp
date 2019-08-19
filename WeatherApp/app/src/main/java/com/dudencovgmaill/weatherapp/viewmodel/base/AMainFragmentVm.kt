package com.dudencovgmaill.weatherapp.viewmodel.base

import android.location.Location
import androidx.lifecycle.LiveData
import com.dudencovgmaill.weatherapp.repo.IRepository
import com.dudencovgmaill.weatherapp.repo.local.models.City

abstract class AMainFragmentVm(repo: IRepository) : ABaseVm(repo) {
    abstract var cityFromEt: String?
    abstract val list: LiveData<List<City>>
    abstract val startMapFragment: LiveData<Long?>

    abstract fun clickOnItem(pos: Int)
    abstract fun clickOnBtn()
    abstract fun setLocation(location: Location?)
}
