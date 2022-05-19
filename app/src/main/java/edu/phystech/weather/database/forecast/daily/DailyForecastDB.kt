package edu.phystech.weather.database.forecast.daily

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.phystech.weather.database.forecast.daily.dao.DailyForecastDao
import edu.phystech.weather.database.forecast.daily.entities.DailyForecastDBEntity


@Database(entities = [DailyForecastDBEntity::class], version = 1)
abstract class DailyForecastDB : RoomDatabase() {
    abstract fun dailyForecastDao(): DailyForecastDao
}
