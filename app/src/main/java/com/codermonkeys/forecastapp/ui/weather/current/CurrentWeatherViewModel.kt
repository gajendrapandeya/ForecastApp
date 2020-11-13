package com.codermonkeys.forecastapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.codermonkeys.forecastapp.data.providers.UnitProvider
import com.codermonkeys.forecastapp.data.repository.ForecastRepository
import com.codermonkeys.forecastapp.internal.UnitSystem
import com.codermonkeys.forecastapp.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    val unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
    get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}