package com.sample.usgsearthquake.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.usgsearthquake.models.EarthquakeCustomResponse
import com.sample.usgsearthquake.repository.FeatureRepository
import com.sample.usgsearthquake.util.NetworkHelper
import com.sample.usgsearthquake.util.Resource
import kotlinx.coroutines.launch

class FeatureViewModel @ViewModelInject constructor(
    private val repository: FeatureRepository,
    val networkHelper: NetworkHelper
) : ViewModel() {


    val earthquakeData: MutableLiveData<Resource<EarthquakeCustomResponse>> = MutableLiveData()
    var earthquakeCustomResponse: EarthquakeCustomResponse? = null

    init {
        getEarthquakes()
    }

    private fun getEarthquakes() = viewModelScope.launch {
        safeGetEarthquakes()
    }

    private suspend fun getAllData() = repository.getFeaturesFromDB()

    private suspend fun safeGetEarthquakes() {
        earthquakeData.postValue(Resource.Loading())

        if (networkHelper.isNetworkConnected()) {
            val resource = repository.getEarthQuakesRemote()
            earthquakeData.postValue(resource)
        } else {
            val fromDb = getAllData()
            if (fromDb == null || fromDb.isEmpty())
                earthquakeData.postValue(Resource.Error("NO Internet or Cache"))
            else
                earthquakeData.postValue(Resource.Success(EarthquakeCustomResponse(getAllData())))
        }

    }

}
