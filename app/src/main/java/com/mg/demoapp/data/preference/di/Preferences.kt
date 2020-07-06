package com.mg.demoapp.data.preference.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.core.KoinComponent

class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("com.mg.demoapp", Context.MODE_PRIVATE)
    private val isUserLoggedIn = "isUserLoggedIn"
    private val userName = "name"

//    init {
//        storeShouldShowFragment(true)
//    }



     fun setIsUserLoggedIn(isUserLoggedInFlag: Boolean) {
        preferences.edit().putBoolean(isUserLoggedIn, isUserLoggedInFlag).apply()
    }

    fun issUserLoggedIn(): Boolean {
        return preferences.getBoolean(isUserLoggedIn, false)
    }

    fun setUserName(name: String){
        preferences.edit().putString(userName, name).apply()
    }

    fun getUserName(): String? {
        return preferences.getString(userName,null)
    }


}