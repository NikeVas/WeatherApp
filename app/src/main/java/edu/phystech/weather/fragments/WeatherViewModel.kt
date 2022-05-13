package edu.phystech.weather.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.phystech.weather.models.TimeListener
import edu.phystech.weather.models.TimeTemperature
import edu.phystech.weather.models.TimeTemperatureService

class WeatherViewModel(
    private val timeTemperatureService : TimeTemperatureService
) : ViewModel() {

    private val _data = MutableLiveData<List<TimeTemperature>>()
    val data : LiveData<List<TimeTemperature>> = _data

    private val listener: TimeListener = {
        _data.value = it
    }

    init {
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        timeTemperatureService.removeListener(listener)
    }

    private fun loadData() {
        timeTemperatureService.addListener(listener)
    }


}