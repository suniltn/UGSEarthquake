package com.sample.usgsearthquake.models


data class Metadata(
    val api: String,
    val count: Int,
    val generated: Long,
    val status: Int,
    val title: String,
    val url: String
)