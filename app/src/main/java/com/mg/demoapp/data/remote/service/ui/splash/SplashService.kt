package com.mg.demoapp.data.remote.service.ui.splash

import com.mg.demoapp.data.model.Splash
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface SplashService {

    @GET("5e8c6e252f00002b0088caf7")
    fun fetchSplashAsync(): Deferred<Splash>
}