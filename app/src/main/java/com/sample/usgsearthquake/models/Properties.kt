package com.sample.usgsearthquake.models


import java.io.Serializable

data class Properties(

    val mag: Double?,
    val url: String?,
    val time: Long?,
    val title: String?,
    val place: String?,
    val ids: String?


    /*val alert: Any,
    val cdi: Any,
    val code: String,
    val detail: String,
    val dmin: Any,
    val felt: Any,
    val gap: Int,
    val ids: String,
    val mag: Double,
    val magType: String,
    val mmi: Any,
    val net: String,
    val nst: Int,
    val place: String,
    val rms: Double,
    val sig: Int,
    val sources: String,
    val status: String,
    val time: Long,
    val title: String,
    val tsunami: Int,
    val type: String,
    val types: String,
    val tz: Any,
    val updated: Long,
    val url: String*/
) : Serializable