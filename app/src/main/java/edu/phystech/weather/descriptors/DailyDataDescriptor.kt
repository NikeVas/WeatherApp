package edu.phystech.weather.descriptors

import edu.phystech.weather.App
import edu.phystech.weather.database.forecast.daily.DailyForecastDB
import edu.phystech.weather.database.forecast.daily.entities.DailyForecastDBEntity
import edu.phystech.weather.weatherapi.OneCallData
import edu.phystech.weather.weatherapi.WeatherAPI

class DailyDataDescriptor(
    private val weatherAPI: WeatherAPI,
    private val database: DailyForecastDB
) {

    private var responseData: OneCallData? = null
    private var tryLoadData = true
    private var existFreshData: Boolean = false

    private fun getCityByCoord(lat: Float, lan: Float): String {
        return "Moscow"
    }

    private suspend fun updateDB() {
        if (!existFreshData) {
            return;
        }

        val city = getCityByCoord(responseData!!.lat!!, responseData!!.lan!!)
        val timezoneOffset = responseData!!.timezone_offset!!
        database.dailyForecastDao().deleteCity(city)

        for (day in responseData!!.daily!!) {
            database.dailyForecastDao().insert(
                DailyForecastDBEntity(
                    city,
                    day.dt!! + timezoneOffset,
                    day.sunrise!!,
                    day.sunset!!,
                    day.temp!!.morn!!,
                    day.temp.day!!,
                    day.temp.eve!!,
                    day.temp.night!!,
                    day.temp.min!!,
                    day.temp.max!!,
                    day.feels_like!!.morn!!,
                    day.feels_like.day!!,
                    day.feels_like.eve!!,
                    day.feels_like.night,
                    day.humidity!!,
                    day.wind_speed!!,
                    day.wind_deg!!,
                    day.uvi!!,
                    day.weather!![0].icon!!
                )
            )
        }

    }

    private suspend fun tryLoadData(lat: Float, lan: Float) {
        if (tryLoadData) {
            responseData = weatherAPI.oneCallApi(lat, lan, App.WEATHER_TOKEN)
            tryLoadData = false
            existFreshData = (responseData != null)
            updateDB()
        }
    }

    fun data(city|coord, callback ) : Data




}