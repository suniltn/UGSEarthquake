package com.sample.usgsearthquake.db

/*@Database(
    entities = [Feature::class],
    version = 1
)*/

/*
@Database(
        entities = [EarthquakeData::class],
        version = 1
)

@TypeConverters(Converters::class)
abstract class FeatureDatabase :RoomDatabase(){

    abstract fun getFeatureDao() : FeatureDao

    companion object {

        private var instance : FeatureDatabase? = null
        private  val LOCK = Any()

        operator fun invoke(context : Context) = instance?: synchronized(LOCK) {
            instance?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FeatureDatabase::class.java,
            "earthquake_db.db"

        ).build()



    }
}*/
