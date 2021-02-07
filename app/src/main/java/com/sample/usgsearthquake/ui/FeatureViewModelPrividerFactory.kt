package com.sample.usgsearthquake.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.usgsearthquake.repository.FeatureRepository

class FeatureViewModelPrividerFactory(val app: Application, val repository: FeatureRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeatureViewModel(repository, app) as T
    }
}