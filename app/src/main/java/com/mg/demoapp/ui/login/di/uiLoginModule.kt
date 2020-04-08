package com.mg.demoapp.ui.login.di

import com.mg.demoapp.ui.login.LoginViewModel
import com.mg.demoapp.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiLoginModule = module {
    viewModel { LoginViewModel(androidApplication()) }
}