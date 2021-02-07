package com.sample.usgsearthquake.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.usgsearthquake.models.EarthquakeData

/*@Database(
    entities = [Feature::class],
    version = 1
)*/

@Database(
        entities = [EarthquakeData::class],
        version = 1
)

@TypeConverters(Converters::class)
abstract class EarthquakeDatabase : RoomDatabase() {

    abstract fun getFeatureDao(): FeatureDao

    companion object {

        private var instance: EarthquakeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                EarthquakeDatabase::class.java,
                "earthquake_new_db.db"

        ).build()


    }
}