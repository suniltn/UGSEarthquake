package com.sample.usgsearthquake.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.usgsearthquake.R
import com.sample.usgsearthquake.models.EarthquakeData
import kotlinx.android.synthetic.main.earthquake_item_view.view.*


class EarthquakeAdapter : RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {
    private val TAG = "EarthquakeAdapter"
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<EarthquakeData>() {
        override fun areItemsTheSame(oldItem: EarthquakeData, newItem: EarthquakeData): Boolean {
            return oldItem.identifier == newItem.identifier
        }

        override fun areContentsTheSame(oldItem: EarthquakeData, newItem: EarthquakeData): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitToDifferAsList(data: List<EarthquakeData>) {
        differ.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.earthquake_item_view,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val earthquakeData = differ.currentList[position]
        holder.itemView.apply {
            loction_value.text = earthquakeData.location
            time_value_1.text = earthquakeData.quakeTime
            magnititude_value_1.text = earthquakeData.magnitude.toString()
            coordinates_value_1.text = formatLocation(earthquakeData)
            setOnClickListener {
                onItemClickListener?.let {
                    it(earthquakeData)
                }
            }
        }
    }

    private fun formatLocation(earthquakeData: EarthquakeData) =
            "(${earthquakeData.longitude.toString()} , ${earthquakeData.latitude.toString()})"

    override fun getItemCount(): Int {
        Log.d(TAG, "Size =  " + differ.currentList.size)
        return differ.currentList.size
    }

    private var onItemClickListener: ((EarthquakeData) -> Unit)? = null

    fun setItemClickListener(listener: (EarthquakeData) -> Unit) {
        onItemClickListener = listener
    }
}