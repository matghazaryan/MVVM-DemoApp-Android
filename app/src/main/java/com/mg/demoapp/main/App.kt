package com.mg.demoapp.main

import android.app.Application
import com.mg.demoapp.common.preference.MGPrefsCacheManager
import com.mg.demoapp.main.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
        configurePrefs()
    }

    private fun configurePrefs() {
        MGPrefsCacheManager().initialize(this@App)
    }

    private fun configureDI() = startKoin {
        androidContext(this@App)
        modules(provideAppComponent())
    }

    private fun provideAppComponent() = appComponent
}