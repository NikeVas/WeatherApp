package edu.phystech.weather.weatherapi

import edu.phystech.weather.CurrentWeatherCity
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    suspend fun currentWeather(
        @Query("q") city: String,
        @Query("appid") appid : String
    ): CurrentWeatherCity

    @GET("onecall")
    suspend fun oneCallApi(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("appid") appid : String
    ) : OneCallData
}