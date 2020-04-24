package com.mg.demoapp.data.local.di

import com.mg.demoapp.data.local.MGDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DATABASE = "DATABASE"

val localModule = module {
    single(named(DATABASE)) { MGDatabase.getDatabase(androidContext()) }
    factory { (get(named(DATABASE)) as MGDatabase).splashDao() }
}