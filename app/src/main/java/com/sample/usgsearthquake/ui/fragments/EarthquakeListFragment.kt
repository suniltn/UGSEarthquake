package com.sample.usgsearthquake.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.usgsearthquake.R
import com.sample.usgsearthquake.adapters.EarthquakeAdapter
import com.sample.usgsearthquake.ui.EarthquakeActivity
import com.sample.usgsearthquake.ui.viewmodel.EarthquakeViewModel
import com.sample.usgsearthquake.util.Resource
import kotlinx.android.synthetic.main.fragment_earthquake.*


class EarthquakeListFragment : Fragment(R.layout.fragment_earthquake) {

    var totalCount: Long = -1;
    var totalItemsProcessed : Long = 0
    lateinit var viewModel: EarthquakeViewModel
    lateinit var earthquakeAdapter: EarthquakeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as EarthquakeActivity).viewModel
        setUpRecyclerView()
        setupScroll()

        earthquakeAdapter.setItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("earthquakeDetail", it.detailsUrl)
            }

            findNavController().navigate(
                    R.id.action_earthquakeListFragment_to_earthquakeDetailsFragment, bundle
            )
        }

        viewModel.earthquakeCount.observe(viewLifecycleOwner, Observer {
            totalCount = it
            Log.d("EBAY", "So the Total Count = $it")
        }
        )

        viewModel.earthquakeData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()

                    response.data?.let { earthquakeResponse ->
                        earthquakeAdapter.submitToDifferAsList(earthquakeResponse.list.toList())
                        totalItemsProcessed += earthquakeResponse.list.size
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(
                                activity,
                                "EBAY an Error oucccured $message",
                                Toast.LENGTH_LONG
                        ).show()
                        Log.e("EBAY", "an Error oucccured $message")

                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun setupScroll() {
        rvEarthquakeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(totalCount > 0 && totalItemsProcessed < totalCount)
                    viewModel.getEarthquakes()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        earthquakeAdapter = EarthquakeAdapter()
        rvEarthquakeList.apply {
            adapter = earthquakeAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }
}
