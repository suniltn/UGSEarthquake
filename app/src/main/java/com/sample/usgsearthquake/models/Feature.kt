package com.sample.usgsearthquake.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
        tableName = "feature"
)

data class Feature(
        @PrimaryKey
        val id: String,
        val geometry: Geometry,
        val properties: Properties,
        val type: String
) : Serializable

