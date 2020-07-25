package com.mg.demoapp.data.remote.service.ui.splash

import com.mg.demoapp.data.model.Splash
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * if 5e8c6e252f00002b0088caf7 will be not available just copy this json on mocky.io webiste and get new id
 * {
id: 1,
avg_time: 1,
currency: "AMD",
isHasData: true
}
 */
interface SplashService {

    @GET("0083f18e-a079-491c-a606-96491fc17142")
    fun fetchSplashAsync(): Deferred<Splash>
}

