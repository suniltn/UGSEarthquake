package com.sample.usgsearthquake.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateConverters {


    companion object {
        fun getDate(timeStamp: Long?): String {
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = timeStamp ?: 1609488000000
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            val sb = StringBuilder()
            return sb.append(year).append("-").append(month).append("-").append(day).toString()
        }

        fun minus1Days(currentDate: Date): String {
            val c = Calendar.getInstance()
            c.time = currentDate
            c.add(Calendar.DATE, -1)
            val currentDateMinus3 = c.time
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.format(currentDateMinus3)
        }

        fun getToday(): String {
            val date = Date()
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.format(date)
        }

        fun dateToString(d: String):Date
        {
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.parse(d)
            //SimpleDateFormat("yyyy-MM-dd").parse(startDate)
        }
    }
}
