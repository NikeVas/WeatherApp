package edu.phystech.weather.utils

import java.time.LocalDateTime

fun currentDay() : String {
    return dateToDay(LocalDateTime.now().toString())
}