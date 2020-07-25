package com.mg.demoapp.ui.login.di

import com.mg.demoapp.ui.login.LoginViewModel
import com.mg.demoapp.ui.login.domain.GetLoginUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiLoginModule = module {
    factory { GetLoginUseCase(get()) }
    viewModel { LoginViewModel(androidApplication(),get(),get()) }
}