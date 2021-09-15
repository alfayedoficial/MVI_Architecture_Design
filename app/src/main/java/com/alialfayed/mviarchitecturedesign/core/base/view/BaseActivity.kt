package com.alialfayed.mviarchitecturedesign.core.base.view

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alialfayed.mviarchitecturedesign.core.base.BaseApp
import com.alialfayed.mviarchitecturedesign.core.di.SharedPrefsHelper
import com.alialfayed.mviarchitecturedesign.core.utils.Extensions
import org.koin.android.ext.android.inject

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/7/2021 - 9:25 PM
 */
abstract class BaseActivity<in T> : AppCompatActivity() where T : ViewDataBinding{

    abstract fun onActivityCreated(dataBinder : T)

    @get:LayoutRes
    protected abstract val layoutResourceId : Int
    protected val sharedPrefsHelper: SharedPrefsHelper by inject()
    protected val extensions : Extensions by inject ()


    final override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        this@BaseActivity.initial()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this@BaseActivity.initial()
    }

    private fun initial(){
        this@BaseActivity.layoutResourceId.let {
            val dataBinder = DataBindingUtil.setContentView<T>(this@BaseActivity , it)
            this@BaseActivity.onActivityCreated(dataBinder)
        }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 1) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }













}