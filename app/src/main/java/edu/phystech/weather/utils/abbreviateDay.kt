package edu.phystech.weather.utils

fun abbreviateDay(day: String) : String {
    return when(day.lowercase()) {
        "понедельник" -> "ПН"
        "вторник" -> "ВТ"
        "среда" -> "СР"
        "четверг" -> "ЧТ"
        "пятница" -> "ПТ"
        "суббота" -> "СБ"
        "воскресенье" -> "ВС"
        else -> ""
    }
}