package com.mg.demoapp.ui.splash

import androidx.lifecycle.*
import com.mg.demoapp.R
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.common.utils.Event
import com.mg.demoapp.data.model.Splash
import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.utils.Resource
import com.mg.demoapp.ui.splash.domain.GetSplashUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SplashViewModel(
    private val getSplashlUseCase: GetSplashUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private var splashSource: LiveData<Resource<Splash>> = MutableLiveData()
    private val _splash = MediatorLiveData<Splash>()
    val splash: LiveData<Splash> get() = _splash
    private val shamObserver: Observer<Splash> = Observer { }
//    private val _isLoading = MutableLiveData<Resource.Status>()

    init {
        _splash.observeForever(shamObserver)
    }

    fun loadDataWhenActivityStarts() {
        getSplash()
    }

    private fun getSplash() = handleRequest(dispatchers, { getSplashlUseCase() }) { result ->
        _splash.removeSource(splashSource) // We make sure there is only one source of livedata (allowing us properly refresh)
        splashSource = result
        _splash.addSource(splashSource) {
            _splash.value = it.data
            if (it.status == Resource.Status.SUCCESS) navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }
    }


    override fun onCleared() {
        super.onCleared()
        _splash.removeObserver(shamObserver)
    }
}