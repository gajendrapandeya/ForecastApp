package com.codermonkeys.forecastapp.ui.weather.future.list

import androidx.lifecycle.ViewModel
import com.codermonkeys.forecastapp.data.providers.UnitProvider
import com.codermonkeys.forecastapp.data.repository.ForecastRepository
import com.codermonkeys.forecastapp.internal.lazyDeferred
import com.codermonkeys.forecastapp.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureListWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weatherEntries by lazyDeferred {
        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
    }
}