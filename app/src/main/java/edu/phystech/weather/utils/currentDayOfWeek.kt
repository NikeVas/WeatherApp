package edu.phystech.weather.utils

import java.time.LocalDateTime
import java.util.*

fun currentDayOfWeek() : String {
    return unixToDayOfWeek(Calendar.getInstance().timeInMillis / 1000)
}