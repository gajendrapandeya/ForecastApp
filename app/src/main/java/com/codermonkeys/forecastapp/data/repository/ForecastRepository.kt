package com.codermonkeys.forecastapp.data.repository

import androidx.lifecycle.LiveData
import com.codermonkeys.forecastapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}