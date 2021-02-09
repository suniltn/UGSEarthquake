package com.sample.usgsearthquake.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sample.usgsearthquake.R
import com.sample.usgsearthquake.ui.EarthquakeActivity
import com.sample.usgsearthquake.ui.viewmodel.EarthquakeViewModel
import kotlinx.android.synthetic.main.fragment_earthquake_deatils.*


class EarthquakeDetailsFragment : Fragment(R.layout.fragment_earthquake_deatils) {

    lateinit var viewModel: EarthquakeViewModel
    val args: EarthquakeDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as EarthquakeActivity).viewModel
        val earthquakeinfo = args.earthquakeDetail

        webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            settings.databaseEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(earthquakeinfo)
                    return false
                }
            }
            Log.d("EBAY", " The url is " + earthquakeinfo)
        }
        webView.loadUrl(earthquakeinfo)
    }
}
