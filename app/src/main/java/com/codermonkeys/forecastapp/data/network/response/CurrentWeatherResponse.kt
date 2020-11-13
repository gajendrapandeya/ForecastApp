package com.codermonkeys.forecastapp.data.network.response

import com.codermonkeys.forecastapp.data.db.entity.CurrentWeatherEntry
import com.codermonkeys.forecastapp.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
        @SerializedName("current")
        val currentWeatherEntry: CurrentWeatherEntry,
        val location: WeatherLocation
)