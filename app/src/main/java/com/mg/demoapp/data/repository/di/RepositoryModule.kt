package com.mg.demoapp.data.repository.di

import com.mg.demoapp.data.repository.AppDispatchers
import com.mg.demoapp.data.repository.ui.splash.SplashRepository
import com.mg.demoapp.data.repository.ui.splash.SplashRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { SplashRepositoryImpl(get(),get()) as SplashRepository }
}