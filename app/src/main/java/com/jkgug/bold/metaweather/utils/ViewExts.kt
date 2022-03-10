package com.jkgug.bold.metaweather.utils

import android.view.View

fun hide(vararg views: View) {
    for (view in views) {
        view.visibility = View.GONE
    }
}

fun show(vararg views: View) {
    for (view in views) {
        view.visibility = View.VISIBLE
    }
}

fun enableViews(vararg views: View) {
    for (view in views) {
        view.isEnabled = true
    }
}

fun disableViews(vararg views: View) {
    for (view in views) {
        view.isEnabled = false
    }
}