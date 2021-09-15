package com.alialfayed.mviarchitecturedesign.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.alialfayed.mviarchitecturedesign.R
import com.alialfayed.mviarchitecturedesign.core.base.BaseApp
import com.alialfayed.mviarchitecturedesign.core.base.view.BaseActivity
import com.alialfayed.mviarchitecturedesign.core.utils.Extensions
import com.alialfayed.mviarchitecturedesign.feature.main.OneSingleActivity
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 4:44 PM
 */
val applicationModule = module() {

    single { SharedPrefsHelper(get()) }

    single { Extensions(get()) }
}

class SharedPrefsHelper(androidApplication: Application) {

    private val mSharedPreferences: SharedPreferences = androidApplication.getSharedPreferences(androidApplication.getString(R.string.app_name)+"1", Context.MODE_PRIVATE)
    private var mSharedPreferencesEditor: SharedPreferences.Editor = mSharedPreferences.edit()

    init {
        mSharedPreferencesEditor.apply()
    }

    fun getStringValue(key: String, defaultValue: String = ""): String {
        return mSharedPreferences.getString(key, defaultValue)!!
    }

    fun setValue(key: String, value: Any?) {
        when (value) {
            is Int? -> {
                mSharedPreferencesEditor.putInt(key, value!!)
                mSharedPreferencesEditor.apply()
            }
            is String? -> {
                mSharedPreferencesEditor.putString(key, value!!)
                mSharedPreferencesEditor.apply()
            }
            is Double? -> {
                mSharedPreferencesEditor.putString(key, value.toString())
                mSharedPreferencesEditor.apply()
            }
            is Long? -> {
                mSharedPreferencesEditor.putLong(key, value!!)
                mSharedPreferencesEditor.apply()
            }
            is Boolean? -> {
                mSharedPreferencesEditor.putBoolean(key, value!!)
                mSharedPreferencesEditor.apply()
            }
        }
    }

    fun getIntValue(key: String, defaultValue: Int): Int {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    fun getLongValue(key: String, defaultValue: Long): Long {
        return mSharedPreferences.getLong(key, defaultValue)
    }

    fun getBooleanValue(keyFlag: String, defaultValue: Boolean = false): Boolean {
        return mSharedPreferences.getBoolean(keyFlag, defaultValue)
    }

    fun removeKey(key: String) {
        mSharedPreferencesEditor.remove(key)
        mSharedPreferencesEditor.apply()
    }

    fun clear() {
        mSharedPreferencesEditor.clear().apply()
    }

}


