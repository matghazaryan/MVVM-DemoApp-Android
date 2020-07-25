package com.mg.demoapp.ui.home.di

import com.mg.demoapp.ui.home.HomeViewModel
import com.mg.demoapp.ui.home.domain.GetTopUsersUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiHomeModule = module {
//    factory { GetTopUsersUseCase(get()) }
    viewModel { HomeViewModel(androidApplication(),get(),get()) }
}