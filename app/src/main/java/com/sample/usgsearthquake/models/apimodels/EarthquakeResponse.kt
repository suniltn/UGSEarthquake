package com.sample.usgsearthquake.models.apimodels


data class EarthquakeResponse(
        //val bbox: List<Double>,
        val features: List<Feature>
        //val metadata: Metadata,
        //val type: String
)