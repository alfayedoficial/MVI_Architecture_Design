package com.alialfayed.mviarchitecturedesign.core.base.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.alialfayed.mviarchitecturedesign.core.base.BaseApp
import com.alialfayed.mviarchitecturedesign.core.di.SharedPrefsHelper
import com.alialfayed.mviarchitecturedesign.core.utils.Extensions
import com.alialfayed.mviarchitecturedesign.core.utils.ExtensionsApp
import org.koin.android.ext.android.inject

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 12:48 AM
 */
abstract class BaseFragment<T> : Fragment() where T:ViewDataBinding{

    @get:LayoutRes
    protected abstract val layoutResourceLayout : Int
    protected val sharedPrefsHelper: SharedPrefsHelper by inject()
    protected val extensions : Extensions by inject ()
    private var status = false

    protected lateinit var dataBinder : T
    lateinit var rootView : View

    abstract fun onFragmentCreated(dataBinder : T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this@BaseFragment.layoutResourceLayout.let {
            dataBinder = DataBindingUtil.inflate<T>(inflater, it, container, false)
            this@BaseFragment.onFragmentCreated(dataBinder)
            setUpViewModelStateObservers()

            rootView = dataBinder.root
            return rootView
        }
    }

    abstract fun setUpViewModelStateObservers()

    protected fun backFragment(){
        requireActivity().onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        if (!extensions.isConnected()) status = true
    }

    override fun onResume() {
        super.onResume()
        if (status) setUpViewModelStateObservers()
    }







}