package com.jkgug.bold.metaweather.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jkgug.bold.metaweather.R

fun Activity.navigate(navDirections: NavDirections) {
    findNavController(R.id.nav_host_fragment_content_main).navigate(navDirections)
}

fun Activity.navigateUp() {
    findNavController(R.id.nav_host_fragment_content_main).navigateUp()
}

fun Fragment.navigate(navDirections: NavDirections) {
    findNavController().navigate(navDirections)
}

fun Fragment.navigate(action: Int) {
    findNavController().navigate(action)
}

fun Fragment.navigateUp() {
    findNavController().navigateUp()
}

