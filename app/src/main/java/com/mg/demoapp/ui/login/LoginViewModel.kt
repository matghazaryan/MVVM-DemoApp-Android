package com.mg.demoapp.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.data.local.MGDatabase
import com.mg.demoapp.data.model.Splash
import kotlinx.coroutines.launch

class LoginViewModel(app: Application) : BaseViewModel(app) {

    private val _splash = MutableLiveData<Splash>()
    val splash: LiveData<Splash> get() = _splash

    init {
        getSplash(app)
    }

    private fun getSplash(app: Application) = launch(coroutineContext) {
        val splashDao = MGDatabase.buildDatabase(app).splashDao()
        _splash.postValue(splashDao.getSplash())
    }

}