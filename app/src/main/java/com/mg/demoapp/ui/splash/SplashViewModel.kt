package com.mg.demoapp.ui.splash

import android.app.Application
import androidx.lifecycle.*
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
    private val shamObserver: Observer<Splash> = Observer { }

    init {
        with(splash) { observeForever(shamObserver) }
    }

    fun loadSplashConfigs() {
        getSplash()
    }

    private fun getSplash() = handleRequest(dispatchers, { splashUseCase() }) { result ->
        splashSource = result
        splash.addSource(splashSource) {
            splash.value = it.data
            if (it.status == Resource.Status.SUCCESS) navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }
    }

    override fun onCleared() {
        super.onCleared()
        splash.removeObserver(shamObserver)
    }
}