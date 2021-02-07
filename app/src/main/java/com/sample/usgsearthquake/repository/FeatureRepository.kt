package com.sample.usgsearthquake.repository

import com.sample.usgsearthquake.api.RetrofitInstance
import com.sample.usgsearthquake.db.EarthquakeDatabase
import com.sample.usgsearthquake.models.EarthquakeCustomResponse
import com.sample.usgsearthquake.models.EarthquakeData
import com.sample.usgsearthquake.models.EarthquakeResponse
import com.sample.usgsearthquake.util.DataConverter
import com.sample.usgsearthquake.util.Extenstions.Companion.round
import com.sample.usgsearthquake.util.Resource
import retrofit2.Response

class FeatureRepository(private val db: EarthquakeDatabase) {

    suspend fun getEarthQuakesRemote(): Resource<EarthquakeCustomResponse> {

        val result: Response<EarthquakeResponse> = RetrofitInstance.api.getEarthquakes()
        return porsess(result)
    }

    private suspend fun porsess(response: Response<EarthquakeResponse>): Resource<EarthquakeCustomResponse> {

        if (response.isSuccessful) {

            response.body()?.let { resultResponse ->

                val customResponse = mapEarthquakeResponseToEarthquakeData(resultResponse)
                deleteAllDB()
                saveData(customResponse.data)
                return customResponse
            }

        }
        return Resource.Error(response.message())

    }

    private suspend fun saveData(response: EarthquakeCustomResponse?) {
        response?.list?.forEach {
            upsert(it)

        }

    }


    private suspend fun mapEarthquakeResponseToEarthquakeData(resultResponse: EarthquakeResponse): Resource<EarthquakeCustomResponse> {

        var list: MutableList<EarthquakeData> = mutableListOf()

        resultResponse.features.forEach {

            val earthquakeData = EarthquakeData(
                    latitude = it.geometry.coordinates[0].round(3),
                    longitude = it.geometry.coordinates[1].round(3),
                    magnitude = it.properties.mag ?: 0.0,
                    quakeTime = DataConverter.getDate(it.properties.time),
                    location = it.properties.place ?: "Unknown",
                    header = it.properties.title ?: "Unknown",
                    detailsUrl = it.properties.url ?: "https://google.com",
                    identifier = it.properties.ids ?: "0")

            list.add(earthquakeData)
        }
        val flist: List<EarthquakeData> = ArrayList<EarthquakeData>(list)

        return Resource.Success(EarthquakeCustomResponse(flist))

    }

    suspend fun upsert(earthquakeData: EarthquakeData) = db.getFeatureDao().upsert(earthquakeData)

    suspend fun getFeaturesFromDB() = db.getFeatureDao().getEarthquakes()

    suspend fun deleteAllDB() = db.getFeatureDao().deleteAllEntries()

}