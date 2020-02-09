package com.dudencovgmaill.weatherapp.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dudencovgmaill.weatherapp.repo.IRepository
import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.dudencovgmaill.weatherapp.viewmodel.base.AMainFragmentVm
import com.github.ajalt.timberkt.Timber.d
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.roundToInt

open class MainFragmentVm @Inject constructor(repo: IRepository) : AMainFragmentVm(repo) {

    override var cityFromEt: String? = null

    override val list: LiveData<List<City>> = MutableLiveData(ArrayList<City>())

    override val startMapFragment: LiveData<Long?> = MutableLiveData(null)

    protected open var longitude: String? = null
    protected open var latitude: String? = null

    override fun onViewCreated() {
        (startMapFragment as MutableLiveData).value = null
        (list as MutableLiveData).value = list.value?.also { (it as ArrayList).clear() }

        getCity("London") {
            addCityToList(it)
            saveCity(it){}
        }
    }

    override fun clickOnItem(pos: Int) {
        (startMapFragment as MutableLiveData).value = list.value?.get(pos)?.id
    }

    override fun clickOnBtn() {
        if (!cityFromEt.isNullOrEmpty()) getCity(cityFromEt!!) { addCityToList(it, 0) }
    }

    override fun setLocation(location: Location?) {
        val longitude: String? = location?.longitude?.toString()
        val latitude: String? = location?.latitude?.toString()

        if (longitude != null && latitude != null && longitude != this.longitude && latitude != this.latitude) {
            this.longitude = longitude
            this.latitude = latitude

            getCity(latitude, longitude) { city ->
                if (city != null && !list.value.isNullOrEmpty()) {
                    val res = list.value!!.filterNot {
                        it.name == city.name
                    } as ArrayList

                    res.add(city)

                    (list as MutableLiveData).value = res
                }
            }
        }
    }

    protected open fun addCityToList(city: City?, i: Int? = null) {
        city?.let {
            (list as MutableLiveData).value = list.value.apply {
                if (i != null) (this as ArrayList).add(i, city) else
                    (this as ArrayList).add(city)
            }
        }
    }
}