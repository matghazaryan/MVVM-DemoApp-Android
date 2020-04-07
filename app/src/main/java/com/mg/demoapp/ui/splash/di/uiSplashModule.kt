package com.mg.demoapp.ui.splash.di

import com.mg.demoapp.ui.splash.SplashViewModel
import com.mg.demoapp.ui.splash.domain.GetSplashUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiSplashModule = module {

    factory { GetSplashUseCase(get()) }
    viewModel { SplashViewModel(get(),get()) }
}