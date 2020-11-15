package com.codermonkeys.forecastapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codermonkeys.forecastapp.data.db.entity.FutureWeatherEntry
import com.codermonkeys.forecastapp.data.db.unitlocalized.future.detail.ImperialDetailFutureWeatherEntry
import com.codermonkeys.forecastapp.data.db.unitlocalized.future.detail.MetricDetailFutureWeatherEntry
import com.codermonkeys.forecastapp.data.db.unitlocalized.future.list.ImperialSimpleFutureWeatherEntry
import com.codermonkeys.forecastapp.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

@Dao
interface FutureWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(futureWeatherEntries: List<FutureWeatherEntry>)

    @Query("SELECT * FROM future_weather WHERE date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastMetric(startDate: LocalDate): LiveData<List<MetricSimpleFutureWeatherEntry>>

    @Query("SELECT * FROM future_weather WHERE date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastImperial(startDate: LocalDate): LiveData<List<ImperialSimpleFutureWeatherEntry>>

    @Query("SELECT * FROM future_weather WHERE date(date) == date(:date)")
    fun getDetailedWeatherByDateMetric(date: LocalDate): LiveData<MetricDetailFutureWeatherEntry>

    @Query("SELECT * FROM future_weather WHERE date(date) == date(:date)")
    fun getDetailedWeatherByDateImperial(date: LocalDate): LiveData<ImperialDetailFutureWeatherEntry>

    @Query("SELECT count(id) FROM future_weather WHERE date(date) >= date(:startDate) ")
    fun countFutureWeather(startDate: LocalDate): Int

    @Query("DELETE FROM future_weather WHERE date(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)
}