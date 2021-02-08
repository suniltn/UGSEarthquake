package com.sample.usgsearthquake.repository

import com.sample.usgsearthquake.models.EarthquakeCustomResponse
import com.sample.usgsearthquake.models.EarthquakeData
import com.sample.usgsearthquake.util.Resource

interface EarthquakeRepository {

    suspend fun getEarthQuakesRemote(startDate: String, endDate: String): Resource<EarthquakeCustomResponse>

    suspend fun getEarthQuakesRemoteCount(): Long

    suspend fun getEarthquakeLocal(): MutableList<EarthquakeData>

    suspend fun deleteAllDB()
}