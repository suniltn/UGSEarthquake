package com.sample.usgsearthquake.repository

import com.sample.usgsearthquake.api.EarthquakeAPI
import com.sample.usgsearthquake.db.EarthquakeDao
import com.sample.usgsearthquake.models.EarthquakeCustomResponse
import com.sample.usgsearthquake.models.EarthquakeData
import com.sample.usgsearthquake.models.apimodels.EarthquakeResponse
import com.sample.usgsearthquake.util.DateConverters
import com.sample.usgsearthquake.util.Extenstions.Companion.round
import com.sample.usgsearthquake.util.Resource
import retrofit2.Response
import javax.inject.Inject


class EarthquakeRepositoryImpl @Inject constructor(private val apis: EarthquakeAPI, private val earthquakeDao: EarthquakeDao) : EarthquakeRepository {

    override suspend fun getEarthQuakesRemote(startDate: String, endDate: String): Resource<EarthquakeCustomResponse> {

        val result: Response<EarthquakeResponse> = apis.getEarthquakes(startTime = startDate, endTime = endDate)
        return porsess(result)
    }

    override suspend fun getEarthQuakesRemoteCount(): Long {
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
                    quakeTime = DateConverters.getDate(it.properties.time),
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

    private suspend fun upsert(earthquakeData: EarthquakeData) = earthquakeDao.upsert(earthquakeData)

    override suspend fun getEarthquakeLocal() = earthquakeDao.getEarthquakes()

    override suspend fun deleteAllDB() = earthquakeDao.deleteAllEntries()

}