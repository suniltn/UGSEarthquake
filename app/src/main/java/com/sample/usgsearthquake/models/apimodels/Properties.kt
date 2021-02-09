package com.sample.usgsearthquake.models.apimodels


import java.io.Serializable

data class Properties(
        val mag: Double?,
        val url: String?,
        val time: Long?,
        val title: String?,
        val place: String?,
        val ids: String?) : Serializable