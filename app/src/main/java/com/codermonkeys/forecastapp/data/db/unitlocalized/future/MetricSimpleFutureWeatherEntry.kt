package com.codermonkeys.forecastapp.data.db.unitlocalized.future

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

data class MetricSimpleFutureWeatherEntry(
    @ColumnInfo(name = "date")
    override val date: LocalDate,
    @ColumnInfo(name = "avgtemp_c")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String
) : UnitSpecificSimpleFutureWeatherEntry