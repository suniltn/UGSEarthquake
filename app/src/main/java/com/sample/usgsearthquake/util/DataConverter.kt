package com.sample.usgsearthquake.util

import java.util.*

class DataConverter {

    companion object {
        fun getDate(timeStamp: Long?): String {
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = timeStamp ?: 1609488000000

            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

            val sb = StringBuilder()
            return sb.append(month).append("/").append(day).append("/").append(year).toString()

        }


    }
}