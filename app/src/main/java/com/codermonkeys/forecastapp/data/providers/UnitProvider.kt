package com.codermonkeys.forecastapp.data.providers

import com.codermonkeys.forecastapp.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}