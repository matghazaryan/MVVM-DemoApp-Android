package com.mg.demoapp.common.preference

import android.content.Context
import android.content.SharedPreferences


class MGPrefsCacheManager {

    private var instance: MGPrefsCacheManager? = null
    private var mSharedPreferences: SharedPreferences? = null

    fun getInstance(): MGPrefsCacheManager? {
        if (instance == null) {
            instance = MGPrefsCacheManager()
        }
        return instance
    }

    fun initialize(ctxt: Context) {
        mSharedPreferences = ctxt.getSharedPreferences(ctxt.getPackageName(), Context.MODE_PRIVATE)
    }

    fun putInCashe(key: String?, value: String?) {
        mSharedPreferences!!.edit().putString(key, value).apply()
    }

    fun putInCashe(key: String?, value: Int) {
        mSharedPreferences!!.edit().putString(key, Integer.toString(value)).apply()
    }

    fun putInCashe(key: String?, value: Boolean) {
        mSharedPreferences!!.edit().putString(key, java.lang.Boolean.toString(value)).apply()
    }

    fun putInCashe(key: String?, value: Float) {
        mSharedPreferences!!.edit().putString(key, java.lang.Float.toString(value)).apply()
    }

    fun putInCashe(key: String?, value: Double) {
        mSharedPreferences!!.edit().putString(key, java.lang.Double.toString(value)).apply()
    }

    fun putInCashe(key: String?, value: Long) {
        mSharedPreferences!!.edit().putString(key, java.lang.Long.toString(value)).apply()
    }

    fun getStringFromCashe(key: String?, defValue: String?): String? {
        val v = mSharedPreferences!!.getString(key, null)
        return v ?: defValue
    }

    fun getIntFromCashe(key: String?, defValue: Int): Int {
        val v = mSharedPreferences!!.getString(key, null)
        return v?.toInt() ?: defValue
    }

    fun getBooleanFromCashe(key: String?, defValue: Boolean): Boolean {
        val v = mSharedPreferences!!.getString(key, null)
        return if (v != null) java.lang.Boolean.parseBoolean(v) else defValue
    }

    fun getFloatFromCache(key: String?, defValue: Float): Float {
        val v = mSharedPreferences!!.getString(key, null)
        return v?.toFloat() ?: defValue
    }

    fun getDoubleFromCache(key: String?, defValue: Double): Double {
        val v = mSharedPreferences!!.getString(key, null)
        return v?.toDouble() ?: defValue
    }

    fun getLongFromCache(key: String?, defValue: Long): Long {
        val v = mSharedPreferences!!.getString(key, null)
        return v?.toLong() ?: defValue
    }

    fun clearCache() {
        mSharedPreferences!!.edit().clear().apply()
    }

    fun removeCacheItem(key: String?) {
        mSharedPreferences!!.edit().remove(key).apply()
    }
}