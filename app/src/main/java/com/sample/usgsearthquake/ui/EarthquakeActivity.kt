package com.sample.usgsearthquake.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sample.usgsearthquake.R
import com.sample.usgsearthquake.ui.viewmodel.EarthquakeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EarthquakeActivity : AppCompatActivity() {

    val viewModel: EarthquakeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake)

        // val repository = FeatureRepository(EarthquakeDatabase(this))
        //  val viewModelProviderFactory = FeatureViewModelPrividerFactory(application, repository)
        // viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FeatureViewModel::class.java)
    }
}