package com.codermonkeys.forecastapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.codermonkeys.forecastapp.data.repository.ForecastRepository
import com.codermonkeys.forecastapp.internal.UnitSystem
import com.codermonkeys.forecastapp.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC//get from Settings later

    val isMetric: Boolean
    get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}