package edu.phystech.weather.utils

fun unixToDayOfWeek(time: Long) : String {
    val sdf = java.text.SimpleDateFormat("EEEE")
    val date = java.util.Date(time * 1000)
    return sdf.format(date)
}