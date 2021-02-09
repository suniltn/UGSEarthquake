package com.sample.usgsearthquake.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.usgsearthquake.models.apimodels.Geometry
import com.sample.usgsearthquake.models.apimodels.Properties

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

