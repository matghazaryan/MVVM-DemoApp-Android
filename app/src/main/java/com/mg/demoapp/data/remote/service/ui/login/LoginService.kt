package com.mg.demoapp.data.remote.service.ui.login

import com.mg.demoapp.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface LoginService {

    @GET("5ea305594f00005f29d9f704")
    fun loginAsyncSuccess(): Deferred<User>
    @GET("5ea54d4f3000008050ce2ed1")
    fun loginAsyncErrorMessage(): Deferred<User>
}