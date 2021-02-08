package com.sample.usgsearthquake.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.usgsearthquake.models.EarthquakeData

@Database(entities = [EarthquakeData::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun featureDao(): FeatureDao
}