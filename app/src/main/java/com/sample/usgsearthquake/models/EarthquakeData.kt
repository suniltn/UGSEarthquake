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
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (magnitude != other.magnitude) return false
        if (detailsUrl != other.detailsUrl) return false
        if (quakeTime != other.quakeTime) return false
        if (location != other.location) return false
        if (header != other.header) return false

        return true
    }

    override fun hashCode(): Int {
        var result = identifier.hashCode()
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + magnitude.hashCode()
        result = 31 * result + detailsUrl.hashCode()
        result = 31 * result + quakeTime.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + header.hashCode()
        return result
    }
}