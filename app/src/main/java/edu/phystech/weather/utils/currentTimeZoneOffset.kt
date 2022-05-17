package edu.phystech.weather.utils

import java.util.*

fun currentTimeZoneOffset() : Long {
    return GregorianCalendar().timeZone.rawOffset.toLong() / 1000
}