package com.sample.usgsearthquake.repository

import com.sample.usgsearthquake.api.EarthquakeAPI
import com.sample.usgsearthquake.db.FeatureDao
import com.sample.usgsearthquake.models.EarthquakeCustomResponse
import com.sample.usgsearthquake.models.EarthquakeData
import com.sample.usgsearthquake.models.EarthquakeResponse
import com.sample.usgsearthquake.util.DataConverter
import com.sample.usgsearthquake.util.Extenstions.Companion.round
import com.sample.usgsearthquake.util.Resource
import retrofit2.Response
import javax.inject.Inject

class FeatureRepository @Inject constructor(val apis: EarthquakeAPI, val featureDao: FeatureDao) {

    suspend fun getEarthQuakesRemote(startDate: String, endDate: String): Resource<EarthquakeCustomResponse> {

        val result: Response<EarthquakeResponse> = apis.getEarthquakes(startTime = startDate, endTime = endDate)
        return porsess(result)
    }

    suspend fun getEarthQuakesRemoteCount(): Long {
        return apis.getEarthquakesCount()
    }

    private suspend fun porsess(response: Response<EarthquakeResponse>): Resource<EarthquakeCustomResponse> {

        if (response.isSuccessful) {

            response.body()?.let { resultResponse ->

                val customResponse = mapEarthquakeResponseToEarthquakeData(resultResponse)
                // deleteAllDB()
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
                    magnitude = it.properties.mag?.round(2) ?: 0.0,
                    quakeTime = DataConverter.getDate(it.properties.time),
                    location = it.properties.place ?: "Unknown",
                    header = it.properties.title ?: "Unknown",
                    detailsUrl = it.properties.url ?: "https://google.com",
                    identifier = it.properties.ids ?: "0"

            )

            list.add(earthquakeData)
        }
        val flist: MutableList<EarthquakeData> = ArrayList<EarthquakeData>(list)

        return Resource.Success(EarthquakeCustomResponse(flist))

    }

    suspend fun upsert(earthquakeData: EarthquakeData) = featureDao.upsert(earthquakeData)

    suspend fun getFeaturesFromDB() = featureDao.getEarthquakes()

    suspend fun deleteAllDB() = featureDao.deleteAllEntries()

}