package edu.phystech.weather.utils

import java.util.*

fun currentTime() : Long {
    val current = Calendar.getInstance().time
    return current.time / 1000
}