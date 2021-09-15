package com.alialfayed.mviarchitecturedesign.core.utils

import android.view.View

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/14/2021 - 1:34 AM
 */

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.inShow() {
    visibility = View.INVISIBLE
}
