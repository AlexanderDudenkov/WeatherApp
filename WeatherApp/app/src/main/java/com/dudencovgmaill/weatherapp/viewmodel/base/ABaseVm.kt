package com.dudencovgmaill.weatherapp.viewmodel.base

import androidx.lifecycle.ViewModel
import com.dudencovgmaill.weatherapp.repo.IRepository
import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.github.ajalt.timberkt.Timber
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class ABaseVm(val repo: IRepository) : ViewModel(), ILifecycleFragments {

    var cd = CompositeDisposable()

    override fun onViewCreated() {}

    override fun onCleared() {
        cd.dispose()
        super.onCleared()
    }

    fun getCity(city: String, setListener: (city: City?) -> Unit) {
        cd.add(
            repo.remoteRepo.getResponse(city)
                .observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    Timber.d { it.toString() }
                    setListener(it)
                }, { t -> Timber.d { t.message.toString() } })!!
        )
    }

    fun getCity(lat: String, lon: String, setListener: (city: City?) -> Unit) {
        cd.add(
            repo.remoteRepo.getResponse(lat, lon)
                .observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    Timber.d { it.toString() }
                    setListener(it)
                }, { t -> Timber.d { t.message.toString() } })!!
        )
    }

    fun getCity(id: Long, setListener: (city: City) -> Unit) {
        cd.add(
            repo.localRepo.getCity(id)
                .observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    Timber.d { it.toString() }
                    setListener(it)
                }, { t -> Timber.d { t.message.toString() } })!!
        )
    }

    fun saveCity(city: City?, setListener: ((id: Long) -> Unit)? = null) {
        if (city != null)
            cd.add(
                Single.fromCallable {
                    repo.localRepo.insertModel(city)
                }.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeOn(Schedulers.io())
                    ?.subscribe({
                        Timber.d { it.toString() }
                        setListener?.invoke(it)
                    }, { t -> Timber.d { t.message.toString() } })!!
            )
    }
}