package com.mg.demoapp.ui.splash

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import com.mg.demoapp.R
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.data.model.Splash
import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.utils.Resource
import com.mg.demoapp.ui.splash.domain.GetSplashUseCase


class SplashViewModel(
    app: Application,
    private val splashUseCase: GetSplashUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel(app) {

    private var splashSource: LiveData<Resource<Splash>> = MutableLiveData()
    private val splash = MediatorLiveData<Splash>()
    private val fakeObserver: Observer<Splash> = Observer { }

    init {
        with(splash) { observeForever(fakeObserver) }
    }

    fun loadSplashConfigs() {
        getSplash()
    }

    private fun getSplash() {
        handleRequest(dispatchers, { splashUseCase() }) { result ->
            splashSource = result
            splash.addSource(splashSource) {
                if (it.status == Resource.Status.SUCCESS)
                    navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment(),NavOptions.Builder().setPopUpTo(
                        R.id.splashFragment,true).build())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        splash.removeObserver(fakeObserver)
    }
}