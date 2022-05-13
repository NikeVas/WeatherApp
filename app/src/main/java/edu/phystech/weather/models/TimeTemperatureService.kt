package edu.phystech.weather.models

typealias TimeListener = (data: List<TimeTemperature>) -> Unit

class TimeTemperatureService {
    private var data = mutableListOf<TimeTemperature>()
    private val listeners = mutableSetOf<TimeListener>()

    init {
        data = (0..24).map {
            TimeTemperature (
                time = "00:00",
                temperature = 21,
            )
        }.toMutableList()
    }

    fun getData(): List<TimeTemperature> {
        return data
    }

    fun removeFirst() {
        data.removeFirst()
        notifyChanges()
    }

    fun removeLast() {
        data.removeLast()
        notifyChanges()
    }

    fun addListener(listener: TimeListener) {
        listeners.add(listener)
        listener.invoke(data)
    }

    fun removeListener(listener: TimeListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(data) }
    }
}