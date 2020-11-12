package com.codermonkeys.forecastapp.data.network

import androidx.lifecycle.LiveData
import com.codermonkeys.forecastapp.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather :LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}