package com.codermonkeys.forecastapp.data.network

import androidx.lifecycle.LiveData
import com.codermonkeys.forecastapp.data.network.response.CurrentWeatherResponse
import com.codermonkeys.forecastapp.data.network.response.FutureWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather :LiveData<CurrentWeatherResponse>
    val downloadedFuturetWeather :LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )

    suspend fun fetchFutureWeather(
        location: String,
        languageCode: String
    )
}