package com.dudencovgmaill.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dudencovgmaill.weatherapp.R
import com.dudencovgmaill.weatherapp.di.injector
import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.dudencovgmaill.weatherapp.tools.inflate
import com.dudencovgmaill.weatherapp.view.base.ABaseFragment
import com.dudencovgmaill.weatherapp.viewmodel.MapFragmentVm
import com.dudencovgmaill.weatherapp.viewmodel.base.AMapFragmentVm
import com.github.ajalt.timberkt.Timber.d
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

open class MapFragment : ABaseFragment(), OnMapReadyCallback {

    protected open var mapView: MapView? = null
    protected open var map: GoogleMap? = null
    protected open var city: City? = null

    protected open val vm: AMapFragmentVm by lazy {
        ViewModelProviders.of(this, activity?.injector?.mapViewModelFactory()).get(MapFragmentVm::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = container?.inflate(R.layout.fragment_map)

        mapView = view?.findViewById<MapView?>(R.id.map_view)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { vm.setCityId(MapFragmentArgs.fromBundle(it).id) }

        vm.city.observe(this, Observer { this.city = it; addMarker(map, it) })
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onMapReady(p0: GoogleMap?) {
        d { "onMapReady; mapType=${p0?.mapType}" }
        map = p0
    }

    protected open fun addMarker(map: GoogleMap?, city: City?) {
        if (map != null && city != null)
            map.addMarker(
                MarkerOptions()
                    .position(LatLng(city.latitude.toDouble(), city.latitude.toDouble()))
                    .title(city.name)
                    .snippet(city.temperature + " " + city.unit)
            )
    }
}