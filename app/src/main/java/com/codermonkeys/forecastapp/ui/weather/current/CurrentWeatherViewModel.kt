package com.codermonkeys.forecastapp.ui.weather.current

import com.codermonkeys.forecastapp.data.providers.UnitProvider
import com.codermonkeys.forecastapp.data.repository.ForecastRepository
import com.codermonkeys.forecastapp.internal.lazyDeferred
import com.codermonkeys.forecastapp.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    val unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }
}