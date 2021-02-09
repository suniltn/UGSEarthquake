package com.sample.usgsearthquake.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
        tableName = "earthquakedata"
)
class EarthquakeData(
        @PrimaryKey
        val identifier: String,
        val latitude: Double,
        val longitude: Double,
        val magnitude: Double,
        val detailsUrl: String,
        val quakeTime: String,
        val location: String,
        val header: String
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EarthquakeData) return false

        if (identifier != other.identifier) return false
        return true
    }

    override fun hashCode(): Int {
        return identifier.hashCode()
    }
}