package com.jkgug.bold.metaweather.utils

fun String.toKms(): String {
    return """${this}Km/h"""
}

fun String.toCelsius(): String {
    return """${this}°C"""
}

fun String.toPercent(): String {
    return """${this}%"""
}