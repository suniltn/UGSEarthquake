package com.sample.usgsearthquake.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.usgsearthquake.models.Geometry
import com.sample.usgsearthquake.models.Properties

class Converters {


    @TypeConverter
    fun fromProperties(value: Properties): String {
        val gson = Gson()
        val type = object : TypeToken<Properties>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toProperties(value: String): Properties {
        val gson = Gson()
        val type = object : TypeToken<Properties>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toGeometry(value: String): Geometry {
        val gson = Gson()
        val type = object : TypeToken<Geometry>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGeometry(value: Geometry): String {
        val gson = Gson()
        val type = object : TypeToken<Properties>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toListOfDoubles(value: String): List<Double> {
        val listType = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListOfDoubles(list: List<Double>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}


/*val map = mutableMapOf<String,String>()
with(map)
{
    put(Constants.IDS, properties.ids?:"NONE")
    put(Constants.MAG, properties.mag.toString())
    put(Constants.URL, properties.url?:"NONE")
    put(Constants.TIME, properties.time.toString())
    put(Constants.PLACE, properties.place?:"NONE")
    put(Constants.TITLE, properties.title?:"NONE")
    put(Constants.UPDATED, properties.updated.toString())
}


@TypeConverter
fun fromProperty(properties: Properties) : Map<String,String> {

val map = mutableMapOf<String,String>()
with(map)
{
    put(Constants.IDS, properties.ids?:"NONE")
    put(Constants.MAG, properties.mag.toString())
    put(Constants.URL, properties.url?:"NONE")
    put(Constants.TIME, properties.time.toString())
    put(Constants.PLACE, properties.place?:"NONE")
    put(Constants.TITLE, properties.title?:"NONE")
    put(Constants.UPDATED, properties.updated.toString())
}

return map
}

@TypeConverter
fun toProperty(map :Map<String,String>) = Properties (
ids = map[Constants.IDS],
mag = map[Constants.MAG]?.toDouble(),
url = map[Constants.URL],
time = map[Constants.TIME]?.toLong(),
place = map[Constants.PLACE],
title = map[Constants.TITLE],
updated = map[Constants.UPDATED]?.toLong()
)

@TypeConverter
fun fromGeometry(geometry: Geometry) = geometry.coordinates[0].toString() +"-"+ geometry.coordinates[1].toString()

@TypeConverter
fun toGeometry(lat : String, long :String) : Geometry{
var list = listOf<Double>(lat.toDouble(),long.toDouble())
return Geometry(list)
*/


/*


 */
