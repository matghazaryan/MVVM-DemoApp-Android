package com.mg.demoapp.data.remote.service.ui.login

class LoginDataSource(private val loginService: LoginService) {
    fun loginAsyncSuccessAsync() = loginService.loginSuccessAsync()
    fun loginAsyncErrorMessageAsync() = loginService.loginErrorMessageAsync()
}
