package edu.phystech.weather.models

import android.util.Log
import edu.phystech.weather.App
import edu.phystech.weather.descriptors.HourlyDataDescriptor
import edu.phystech.weather.descriptors.entities.Hour

typealias TimeListener = (data: List<Hour>) -> Unit

class HourlyForecastService(val city: String) {
    private var data = mutableListOf<Hour>()
    private val listeners = mutableSetOf<TimeListener>()
    private lateinit var descriptor: HourlyDataDescriptor

    fun create(application: App) {
        Log.d("aboba", "Create service")
        descriptor = application.hourlyDataDescriptor
        descriptor.data(city, { newData ->
            data = newData.hours.toMutableList()
            Log.e("data", "update data")
            notifyChanges()
        })
    }

    fun getData() : List<Hour> {
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
        Log.e("addListener", "invoke")
        listener.invoke(data)
    }

    fun removeListener(listener: TimeListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(data) }
    }
}