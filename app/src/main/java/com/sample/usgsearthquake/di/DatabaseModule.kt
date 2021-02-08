package com.sample.usgsearthquake.di

import android.content.Context
import androidx.room.Room
import com.sample.usgsearthquake.db.AppDatabase
import com.sample.usgsearthquake.db.FeatureDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): FeatureDao {
        return appDatabase.featureDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "earthquake_new_db.db"
        ).build()
    }
}