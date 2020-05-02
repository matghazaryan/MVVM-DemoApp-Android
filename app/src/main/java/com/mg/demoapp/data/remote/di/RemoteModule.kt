package com.mg.demoapp.data.remote.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mg.demoapp.data.remote.service.ui.login.LoginDataSource
import com.mg.demoapp.data.remote.service.ui.login.LoginService
import com.mg.demoapp.data.remote.service.ui.splash.SplashDataSource
import com.mg.demoapp.data.remote.service.ui.splash.SplashService
import com.mg.demoapp.data.repository.utils.ErrorInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRemoteModule(baseUrl: String) = module {
    factory<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory {
        OkHttpClient.Builder().addInterceptor(get() as Interceptor)
            .addInterceptor(ErrorInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>().create(SplashService::class.java) }
    factory { get<Retrofit>().create(LoginService::class.java) }

    factory { SplashDataSource(get()) }
    factory { LoginDataSource(get()) }
}

