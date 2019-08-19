package com.dudencovgmaill.weatherapp.view

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.Timber.d

object CityLocationListener : LocationListener {

    private var list: LiveData<Location?> = MutableLiveData(null)

    fun getLocation(): LiveData<Location?> = list

    override fun onLocationChanged(location: Location?) {
        d { "onLocationChanged" }
        (list as MutableLiveData).postValue(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        d { "onStatusChanged" }
    }

    override fun onProviderEnabled(provider: String?) {
        d { "onProviderEnabled" }
    }

    override fun onProviderDisabled(provider: String?) {
        d { "onProviderDisabled" }
    }
}
/*
object CityLocationListener : LocationListener {

    private var list: LiveData<List<Address>?> = MutableLiveData(null)

    fun getLocation(): LiveData<List<Address>?> = list

    override fun onLocationChanged(location: Location?) {
        d { "onLocationChanged" }
        val geo = Geocoder(App.getInstance(), Locale.getDefault())

        try {
            val addresses = location?.let { geo.getFromLocation(location.latitude, location.longitude, 1) }

            (list as MutableLiveData).postValue(addresses)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        d { "onStatusChanged" }
    }

    override fun onProviderEnabled(provider: String?) {
        d { "onProviderEnabled" }
    }

    override fun onProviderDisabled(provider: String?) {
        d { "onProviderDisabled" }
    }
}*/
