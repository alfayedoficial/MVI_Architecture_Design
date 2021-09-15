package com.alialfayed.mviarchitecturedesign.core.base

import android.app.Application
import com.alialfayed.mviarchitecturedesign.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp : Application(){

    private val modules = listOf(
        detailsViewModelModule , wishListViewModelModule, homeViewModelModule, networkModule , applicationModule , localModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BaseApp)
            modules(modules)

        }

    }



}