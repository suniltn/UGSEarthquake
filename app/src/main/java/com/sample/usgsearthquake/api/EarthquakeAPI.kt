package com.sample.usgsearthquake.api

import com.sample.usgsearthquake.models.EarthquakeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeAPI {

    @GET("/fdsnws/event/1/query")

    suspend fun getEarthquakes(
            @Query("format")
            format: String = "geojson",
            @Query("starttime")
            startTime: String = "2021-02-01",
            @Query("limit")
            limit:Int = 50
    ): Response<EarthquakeResponse>

}