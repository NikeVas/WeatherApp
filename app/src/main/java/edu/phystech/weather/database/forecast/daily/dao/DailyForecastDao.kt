package edu.phystech.weather.database.forecast.daily.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.phystech.weather.database.forecast.daily.entities.DailyForecastDBEntity


@Dao
interface DailyForecastDao {
    @Query("DELETE FROM daily_forecast WHERE city = :city")
    suspend fun deleteCity(city: String)

    @Insert
    suspend fun insert(vararg entity: DailyForecastDBEntity)
}