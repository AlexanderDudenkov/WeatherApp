package com.dudencovgmaill.weatherapp.view.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.dudencovgmaill.weatherapp.R
import com.dudencovgmaill.weatherapp.tools.navigateCustom
import com.dudencovgmaill.weatherapp.view.MainActivity

abstract class ABaseFragment : Fragment() {

    fun navigate(nextFrId: Int) {
        Navigation.findNavController(activity as MainActivity, R.id.nav_host_fragment).navigateCustom(nextFrId)
    }

    fun navigate(nextFrId: Int, action: NavDirections) {
        Navigation.findNavController(activity as MainActivity, R.id.nav_host_fragment).navigateCustom(nextFrId, action)
    }
}