package edu.phystech.weather.database.forecast.hourly

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.phystech.weather.database.forecast.hourly.dao.HourlyForecastDao
import edu.phystech.weather.database.forecast.hourly.entity.HourlyForecastDBEntity


@Database(entities = [HourlyForecastDBEntity::class], version = 1)
abstract class HourlyForecastDB : RoomDatabase() {
    abstract fun hourlyForecastDao(): HourlyForecastDao
}