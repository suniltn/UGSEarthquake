package com.sample.usgsearthquake.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sample.usgsearthquake.R
import com.sample.usgsearthquake.db.EarthquakeDatabase
import com.sample.usgsearthquake.repository.FeatureRepository

class EarthquakeActivity : AppCompatActivity() {

    lateinit var viewModel: FeatureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake)

        val repository = FeatureRepository(EarthquakeDatabase(this))
        val viewModelProviderFactory = FeatureViewModelPrividerFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FeatureViewModel::class.java)
    }
}