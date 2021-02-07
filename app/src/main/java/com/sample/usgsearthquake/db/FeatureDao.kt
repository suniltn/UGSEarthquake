package com.sample.usgsearthquake.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.usgsearthquake.models.EarthquakeData

@Dao
interface FeatureDao {

    @Query("SELECT * FROM earthquakedata")
    suspend fun getEarthquakes(): List<EarthquakeData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(earthquakeData: EarthquakeData): Long

    @Query("DELETE FROM earthquakedata")
    suspend fun deleteAllEntries()


}