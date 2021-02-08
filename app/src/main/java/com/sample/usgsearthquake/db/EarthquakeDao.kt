package com.sample.usgsearthquake.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.usgsearthquake.models.EarthquakeData

@Dao
interface EarthquakeDao {

    @Query("SELECT * FROM earthquakedata")
    suspend fun getEarthquakes(): MutableList<EarthquakeData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(earthquakeData: EarthquakeData): Long

    @Query("DELETE FROM earthquakedata")
    suspend fun deleteAllEntries()


}