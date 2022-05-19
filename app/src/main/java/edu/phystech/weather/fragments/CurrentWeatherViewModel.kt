package edu.phystech.weather.fragments

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.phystech.weather.descriptors.CurrentDataDescriptor
import edu.phystech.weather.descriptors.CurrentDataSetterCallback
import edu.phystech.weather.descriptors.entities.CurrentData
import edu.phystech.weather.descriptors.entities.Day

class CurrentWeatherViewModel(
    private val descriptor: CurrentDataDescriptor,
    private val city: String,
    private val activity: Activity
) : ViewModel() {

    private val _data = MutableLiveData<CurrentData>()
    val data : LiveData<CurrentData> = _data

    private val listener: CurrentDataSetterCallback = {
        activity.runOnUiThread {
            _data.value = it
        }
    }

    init {
        descriptor.data(city, listener)
    }
}