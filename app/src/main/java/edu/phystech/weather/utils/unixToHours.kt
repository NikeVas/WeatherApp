package edu.phystech.weather.utils

fun unixToHours(date: Long) : String {
    return unixToDate(date).toString().substring(11, 16)
}