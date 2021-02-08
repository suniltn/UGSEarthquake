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
            startTime: String,
            @Query("endtime")
            endTime: String


    ): Response<EarthquakeResponse>

    @GET("/fdsnws/event/1/count")
    suspend fun getEarthquakesCount(
            @Query("format")
            format: String = "text"
    ): Long

}