package edu.phystech.weather.utils

import edu.phystech.weather.R

fun map(icon: String) : Int {
    return when(icon) {
        "01d" -> R.drawable.ic_01d
        "02d" -> R.drawable.ic_02d
        "03d" -> R.drawable.ic_03d
        "04d" -> R.drawable.ic_04d
        "09d" -> R.drawable.ic_09d
        "10d" -> R.drawable.ic_10d
        "11d" -> R.drawable.ic_11d
        "13d" -> R.drawable.ic_13d
        "01n" -> R.drawable.ic_01n
        "02n" -> R.drawable.ic_02n
        "03n" -> R.drawable.ic_03n
        "04n" -> R.drawable.ic_04n
        "09n" -> R.drawable.ic_09n
        "10n" -> R.drawable.ic_10n
        "11n" -> R.drawable.ic_11n
        "13n" -> R.drawable.ic_13n
        else -> R.drawable.ic_04d
    }
}

fun mapBackground(icon: String) : Int {
    return when(icon) {
        "01d" -> R.drawable.background_sunny_day
        "02d" -> R.drawable.background_sunny_day
        "03d" -> R.drawable.background_sunny_day
        "04d" -> R.drawable.background_sunny_day
        "09d" -> R.drawable.background_rain
        "10d" -> R.drawable.background_light_rain
        "11d" -> R.drawable.background_rain
        "13d" -> R.drawable.background_rain
        "01n" -> R.drawable.background_night
        "02n" -> R.drawable.background_night
        "03n" -> R.drawable.background_night
        "04n" -> R.drawable.background_night
        "09n" -> R.drawable.background_rain
        "10n" -> R.drawable.background_rain
        "11n" -> R.drawable.background_rain
        "13n" -> R.drawable.background_rain
        else -> R.drawable.background_sunny_day
    }
}