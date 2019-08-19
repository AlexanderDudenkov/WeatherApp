package com.dudencovgmaill.weatherapp.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.dudencovgmaill.weatherapp.R
import com.dudencovgmaill.weatherapp.tools.toast
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        getInternetPermission()
        getLocationPermission()
    }

    override fun onSupportNavigateUp() = findNavController(nav_host_fragment).navigateUp()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocation()
                } else {
                    toast("LOCATION permission denied!")
                }
            }
            REQUEST_INTERNET -> {
                if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    toast("INTERNET permission denied!")
                }
            }
        }
    }

    protected open fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            initLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    protected open fun getInternetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            == PackageManager.PERMISSION_GRANTED
        ) {

        } else {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.INTERNET),
            REQUEST_INTERNET
        )
         }
    }

    @SuppressLint("MissingPermission")
    protected open fun initLocation() {
        val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10f, CityLocationListener)
    }

    companion object {
        const val REQUEST_ACCESS_FINE_LOCATION: Int = 1
        const val REQUEST_INTERNET: Int = 2
    }
}
