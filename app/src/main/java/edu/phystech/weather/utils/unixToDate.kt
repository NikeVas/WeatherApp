package edu.phystech.weather.utils

fun unixToDate(time: Long) : String {
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date = java.util.Date(time * 1000)
    return sdf.format(date)
}