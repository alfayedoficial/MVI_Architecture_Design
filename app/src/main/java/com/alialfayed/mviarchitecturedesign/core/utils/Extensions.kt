package com.alialfayed.mviarchitecturedesign.core.utils

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresPermission

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 6:50 PM
 */
class Extensions(private val androidApplication: Application) {

    /**
     * Handler time 1000 millisecond ==> 1Second
     * throws  ActivityNotFoundException
     */
    fun openWifi(activity : Activity) {
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            try {
                activity.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(androidApplication, "Not installed.", Toast.LENGTH_SHORT).show()
            }
        }, 500.toLong())
    }

    /**
     * Get the network info
     *
     * @param context the context
     * @return network info
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun getNetworkInfo(androidApplication: Application): NetworkInfo? {
        val cm = (this.androidApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        return cm.activeNetworkInfo
    }

    /**
     * Check if there is any connectivity
     *
     * @param context the context
     * @return boolean boolean
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isConnected(): Boolean {
        val info = getNetworkInfo(androidApplication)
        return info != null && info.isConnected
    }
}