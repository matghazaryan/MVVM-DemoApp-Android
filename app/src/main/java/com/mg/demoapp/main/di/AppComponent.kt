package com.mg.demoapp.main.di

import com.mg.demoapp.data.local.di.localModule
import com.mg.demoapp.data.preference.di.prefModule
import com.mg.demoapp.data.remote.di.createRemoteModule
import com.mg.demoapp.data.repository.di.repositoryModule
import com.mg.demoapp.ui.details.di.uiDetailsModule
import com.mg.demoapp.ui.home.di.uiHomeModule
import com.mg.demoapp.ui.login.di.uiLoginModule
import com.mg.demoapp.ui.splash.di.uiSplashModule

val appComponent= listOf(
    createRemoteModule("http://www.mocky.io/v2/"),
    repositoryModule,
    localModule,
    prefModule,
    uiSplashModule,
    uiLoginModule,
    uiHomeModule
//    uiDetailsModule
)