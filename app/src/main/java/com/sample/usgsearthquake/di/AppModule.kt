package com.sample.usgsearthquake.di

import com.sample.usgsearthquake.api.EarthquakeAPI
import com.sample.usgsearthquake.db.FeatureDao
import com.sample.usgsearthquake.repository.FeatureRepository
import com.sample.usgsearthquake.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideEarthquakeAPI(): EarthquakeAPI {
        val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }

        val api by lazy {
            retrofit.create(EarthquakeAPI::class.java)
        }
        return api
    }


    @Singleton
    @Provides
    fun provideFeatureRepository(api: EarthquakeAPI, db: FeatureDao): FeatureRepository {
        return FeatureRepository(api, db)
    }

}