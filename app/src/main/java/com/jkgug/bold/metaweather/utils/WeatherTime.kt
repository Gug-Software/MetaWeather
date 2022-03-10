package com.jkgug.bold.metaweather.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val pattern = "yyyy-MM-dd"
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(Date())
}

fun getCurrentDateForService(): String {
    val pattern = "yyyy/MM/dd"
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(Date())
}

fun String.transformDateToFormatDayName(): String {
    val patternIn = "yyyy-MM-dd"
    val patternOut = "EEE"
    val date = SimpleDateFormat(patternIn).parse(this)
    val simpleDateFormat = SimpleDateFormat(patternOut)
    return simpleDateFormat.format(date)
}

fun String.transformDateToFormatHour(): String {
    val patternIn = "yyyy-MM-dd'T'HH:mm:ss.SSSSS'Z'"
    val patternOut = "EEE HH:mm"
    val date = SimpleDateFormat(patternIn).parse(this)
    val simpleDateFormat = SimpleDateFormat(patternOut)
    return simpleDateFormat.format(date)
}