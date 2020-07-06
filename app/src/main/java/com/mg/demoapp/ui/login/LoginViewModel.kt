package com.mg.demoapp.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mg.demoapp.common.base.BaseViewModel
import com.mg.demoapp.common.constants.MGConstants
import com.mg.demoapp.data.local.MGDatabase
import com.mg.demoapp.data.model.LoginObj
import com.mg.demoapp.data.model.Splash
import com.mg.demoapp.data.model.User
import com.mg.demoapp.data.preference.di.Preferences
import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.utils.Resource
import com.mg.demoapp.ui.login.domain.GetLoginUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class LoginViewModel(app: Application,
                     private val dispatchers: AppDispatchers,
                     private val loginUseCase: GetLoginUseCase) : BaseViewModel(app) {

    private val _splash = MutableLiveData<Splash>()
    val splash: LiveData<Splash> get() = _splash

    private var userSource: LiveData<Resource<User>> = MutableLiveData()
    private val user = MediatorLiveData<User>()
    private val fakeObserver: Observer<User> = Observer { }

    private val preferences: Preferences by inject(Preferences::class.java)


    init {
        with(user) { observeForever(fakeObserver) }
        getSplashFromDB(app)
    }

    private fun getSplashFromDB(app: Application) = launch(coroutineContext) {
        val splashDao = MGDatabase.getDatabase(app).splashDao()
        _splash.postValue(splashDao.getSplash())
    }

    fun chooseLoginType(login: String, password: String) {
        val urlValue = when (login) {
            MGConstants.successUsername -> MGConstants.successLogin
            else -> MGConstants.errorLogin
        }
        LoginObj(login, password, urlValue).also(this::apiLogin)
    }

    private fun apiLogin(login: LoginObj) {
        handleRequest(dispatchers,{ loginUseCase.invoke(login) }){ result ->
            userSource = result
            user.addSource(userSource){
                user.value = it.data
                if (it.status == Resource.Status.SUCCESS) {
                    preferences.setIsUserLoggedIn(true)
                    preferences.setUserName("Serojik")
                    navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        user.removeObserver(fakeObserver)
    }

}
