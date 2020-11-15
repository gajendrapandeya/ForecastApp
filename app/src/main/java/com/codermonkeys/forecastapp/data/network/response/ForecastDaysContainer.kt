package com.codermonkeys.forecastapp.data.network.response


import com.codermonkeys.forecastapp.data.db.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)