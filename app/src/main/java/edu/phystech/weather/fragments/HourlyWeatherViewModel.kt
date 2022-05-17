package edu.phystech.weather.fragments

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.phystech.weather.descriptors.HourlyDataDescriptor
import edu.phystech.weather.descriptors.HourlyDataSetterCallback
import edu.phystech.weather.descriptors.entities.Hour
import edu.phystech.weather.models.TimeListener
import edu.phystech.weather.models.HourlyForecastService

class HourlyWeatherViewModel (
    private val descriptor: HourlyDataDescriptor,
    private val city: String,
    private val activity: Activity
) : ViewModel() {

    private val _data = MutableLiveData<List<Hour>>()
    val data : LiveData<List<Hour>> = _data

    private val listener: HourlyDataSetterCallback = {
        activity.runOnUiThread {
            _data.value = it.hours
        }
    }

    init {
        descriptor.data(city, listener)
    }
}