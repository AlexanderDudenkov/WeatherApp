package com.dudencovgmaill.weatherapp.tools

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import com.github.ajalt.timberkt.Timber.d
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun Context.toast(message: String?) {
    message?.let {
        if (it.isNotEmpty())
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
}

fun Activity?.hideKeyBoard(): Boolean {
    val view = this?.currentFocus
    if (view != null) {
        (this?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        return true
    }
    return false
}

fun ViewGroup.inflate(layoutRes: Int) = inflater().inflate(layoutRes, this, false)

fun ViewGroup.inflater() = LayoutInflater.from(this.context)

fun Fragment.key(): String {
    val key = Fragment::class.java.simpleName
    d { "Fragment.key: $key" }
    return key
}

fun ProgressBar?.showProgress(activity: Activity?, inProgress: Boolean) {
    this?.let {
        activity?.hideKeyBoard()
        Observable.just(inProgress)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { state ->
                if (state)
                    it.visibility = View.VISIBLE
                else
                    it.visibility = View.GONE
            }
    }
}

fun NavController?.navigateCustom(@IdRes nextFrId: Int) {
    if (this?.currentDestination?.id != nextFrId) {
        this?.navigate(nextFrId)
    }
}

fun NavController?.navigateCustom(@IdRes nextFrId: Int, action: NavDirections) {
    if (this?.currentDestination?.id != nextFrId) {
        this?.navigate(action)
    }
}