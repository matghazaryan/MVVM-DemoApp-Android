package com.mg.demoapp.data.remote.service.ui.splash

class SplashDataSource(private val splashService: SplashService) {
    fun fetchSplashAsync() = splashService.fetchSplashAsync()
}