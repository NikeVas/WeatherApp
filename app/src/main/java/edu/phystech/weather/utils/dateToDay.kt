package edu.phystech.weather.utils

fun dateToDay(date: String) : String {
    return date.subSequence(0, 10).toString()
}