package com.mg.demoapp.main

import android.app.Application
import com.mg.demoapp.main.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    // CONFIGURATION ---
    open fun configureDi() = startKoin {
        androidContext(this@App)
        modules(provideComponent())
    }

    // PUBLIC API ---
    open fun provideComponent() = appComponent
}