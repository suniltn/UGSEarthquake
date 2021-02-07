package com.sample.usgsearthquake.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.Network
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.usgsearthquake.EarthquakeApplication
import com.sample.usgsearthquake.models.EarthquakeCustomResponse
import com.sample.usgsearthquake.repository.FeatureRepository
import com.sample.usgsearthquake.util.Resource
import kotlinx.coroutines.launch

class FeatureViewModel(private val repository: FeatureRepository, app: Application) : AndroidViewModel(app) {


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

        if (hasInternetConnection()) {
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


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<EarthquakeApplication>()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val network: Network = connectivityManager.activeNetwork ?: return false
            val fulfill = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                fulfill.hasTransport(TRANSPORT_WIFI) -> true
                fulfill.hasTransport(TRANSPORT_CELLULAR) -> true
                fulfill.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }

        } else {

            connectivityManager.activeNetworkInfo?.run {
                return when (type) {

                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false

                }
            }
        }


        return false


    }

}
