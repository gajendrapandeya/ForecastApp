package com.codermonkeys.forecastapp.data.repository

import androidx.lifecycle.LiveData
import com.codermonkeys.forecastapp.data.db.entity.WeatherLocation
import com.codermonkeys.forecastapp.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.codermonkeys.forecastapp.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import com.codermonkeys.forecastapp.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>
    suspend fun getFutureWeatherByDate(date: LocalDate, metric: Boolean): LiveData<out UnitSpecificDetailFutureWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}