package com.codermonkeys.forecastapp.data.providers

import com.codermonkeys.forecastapp.data.db.entity.WeatherLocation

interface LocationProvider {

    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}