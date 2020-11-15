package com.codermonkeys.forecastapp.ui.base

import androidx.lifecycle.ViewModel
import com.codermonkeys.forecastapp.data.providers.UnitProvider
import com.codermonkeys.forecastapp.data.repository.ForecastRepository
import com.codermonkeys.forecastapp.internal.UnitSystem
import com.codermonkeys.forecastapp.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}