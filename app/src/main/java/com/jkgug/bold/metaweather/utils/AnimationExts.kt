package com.jkgug.bold.metaweather.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.jkgug.bold.metaweather.R

internal inline fun View.slideInLeft(crossinline functionOnAnimationEnd: () -> Unit = {}) {
    isVisible = true
    val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            functionOnAnimationEnd.invoke()
        }
    })
    startAnimation(animation)
}

internal inline fun View.slideInRight(crossinline functionOnAnimationEnd: () -> Unit = {}) {
    isVisible = true
    val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            functionOnAnimationEnd.invoke()
        }
    })
    startAnimation(animation)
}