package edu.phystech.weather.fragments

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.phystech.weather.descriptors.DailyDataDescriptor
import edu.phystech.weather.descriptors.DailyDataSetterCallback
import edu.phystech.weather.descriptors.HourlyDataDescriptor
import edu.phystech.weather.descriptors.entities.Day
import edu.phystech.weather.descriptors.entities.Hour

class DailyWeatherViewModel (
    private val descriptor: DailyDataDescriptor,
    private val city: String,
    private val activity: Activity
) : ViewModel() {

    private val _data = MutableLiveData<List<Day>>()
    val data : LiveData<List<Day>> = _data

    private val listener: DailyDataSetterCallback = {
        activity.runOnUiThread {
            _data.value = it.days
        }
    }

    init {
        descriptor.data(city, listener)
    }
}