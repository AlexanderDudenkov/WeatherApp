package com.dudencovgmaill.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dudencovgmaill.weatherapp.R
import com.dudencovgmaill.weatherapp.tools.inflate
import com.dudencovgmaill.weatherapp.view.base.ABaseFragment

class MapFragment : ABaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.fragment_map)
    }

}