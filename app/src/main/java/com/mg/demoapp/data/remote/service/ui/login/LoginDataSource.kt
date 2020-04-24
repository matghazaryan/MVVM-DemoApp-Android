package com.mg.demoapp.data.remote.service.ui.login

import com.mg.demoapp.data.remote.service.ui.splash.SplashService

class LoginDataSource(private val loginService: LoginService) {
    fun loginAsyncSuccessAsync() = loginService.loginAsyncSuccess()
    fun loginAsyncErrorMessageAsync() = loginService.loginAsyncErrorMessage()
}
