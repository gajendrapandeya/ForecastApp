package com.codermonkeys.forecastapp.data.repository

import androidx.lifecycle.LiveData
import com.codermonkeys.forecastapp.data.db.entity.WeatherLocation
import com.codermonkeys.forecastapp.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}