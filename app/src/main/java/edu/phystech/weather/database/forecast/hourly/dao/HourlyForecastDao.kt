package edu.phystech.weather.database.forecast.hourly.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.phystech.weather.database.forecast.daily.entities.DailyForecastDBEntity
import edu.phystech.weather.database.forecast.hourly.entity.HourlyForecastDBEntity

@Dao
interface HourlyForecastDao {
    @Query("DELETE FROM hourly_forecast WHERE city = :city")
    suspend fun deleteCity(city: String)

    @Query("SELECT * FROM hourly_forecast WHERE city = :city ORDER BY dt")
    suspend fun getCityWeather(city: String) : List<HourlyForecastDBEntity>

    @Insert
    suspend fun insert(vararg entity: HourlyForecastDBEntity)
}