package com.sample.usgsearthquake.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.usgsearthquake.models.EarthquakeCustomResponse
import com.sample.usgsearthquake.repository.EarthquakeRepository
import com.sample.usgsearthquake.util.DateConverters
import com.sample.usgsearthquake.util.NetworkHelper
import com.sample.usgsearthquake.util.Resource
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class EarthquakeViewModel @ViewModelInject constructor(
        private val repository: EarthquakeRepository,
        val networkHelper: NetworkHelper
) : ViewModel() {


    val earthquakeData: MutableLiveData<Resource<EarthquakeCustomResponse>> = MutableLiveData()
    val earthquakeCount: MutableLiveData<Long> = MutableLiveData()
    var earthquakeRespose: Resource<EarthquakeCustomResponse>? = null

    lateinit var startDate: String
    lateinit var endDate: String

    init {
        getEarthquakesCount()

        startDate = DateConverters.minus1Days(Date())
        endDate = DateConverters.getToday();

        getEarthquakes()

    }

    fun getEarthquakes() = viewModelScope.launch {
        safeGetEarthquakes()
    }

    fun getEarthquakesCount() = viewModelScope.launch {
        safeGetEarthquakesCount()
    }

    private suspend fun getAllData() = repository.getEarthquakeLocal()

    private suspend fun safeGetEarthquakes() {
        earthquakeData.postValue(Resource.Loading())

        if (networkHelper.isNetworkConnected()) {
            val resource = repository.getEarthQuakesRemote(startDate, endDate)

            if (earthquakeRespose == null) {
                earthquakeRespose = resource
                endDate = startDate
                startDate = DateConverters.minus1Days(SimpleDateFormat("yyyy-MM-dd").parse(startDate))
            } else {
                val oldData = earthquakeRespose!!.data?.list
                val newData = resource.data?.list

                if (newData != null) {
                    oldData?.addAll(newData)
                    endDate = startDate
                    startDate = DateConverters.minus1Days(SimpleDateFormat("yyyy-MM-dd").parse(startDate))
                }
            }
            earthquakeData.postValue(earthquakeRespose)


        } else {
            val fromDb = getAllData()
            if (fromDb == null || fromDb.isEmpty())
                earthquakeData.postValue(Resource.Error("NO Internet or Cache"))
            else
                earthquakeData.postValue(Resource.Success(EarthquakeCustomResponse(getAllData())))
        }

    }


    private suspend fun safeGetEarthquakesCount() {
        if (networkHelper.isNetworkConnected()) {
            earthquakeCount.postValue(repository.getEarthQuakesRemoteCount())
        } else {
            earthquakeCount.postValue(-1)
        }
    }
}
