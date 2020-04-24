package com.mg.demoapp.data.repository.di

import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.ui.login.DefaultLoginRepository
import com.mg.demoapp.data.repository.ui.login.LoginRepository
import com.mg.demoapp.data.repository.ui.splash.SplashRepository
import com.mg.demoapp.data.repository.ui.splash.DefaultSplashRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    factory { DefaultSplashRepository(get(),get()) as SplashRepository }
    factory { DefaultLoginRepository(get()) as LoginRepository }


}