package com.codermonkeys.forecastapp.data.providers

import com.codermonkeys.forecastapp.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
       return "Dhangadhi"
    }
}