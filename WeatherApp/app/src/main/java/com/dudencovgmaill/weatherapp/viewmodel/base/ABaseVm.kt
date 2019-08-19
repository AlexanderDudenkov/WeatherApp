package com.dudencovgmaill.weatherapp.viewmodel.base

import androidx.lifecycle.ViewModel
import com.dudencovgmaill.weatherapp.repo.IRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class ABaseVm(val repo: IRepository) : ViewModel(), ILifecycleFragments {

    var cd = CompositeDisposable()

    override fun onViewCreated() {}

    override fun onCleared() {
        cd.dispose()
        super.onCleared()
    }
}