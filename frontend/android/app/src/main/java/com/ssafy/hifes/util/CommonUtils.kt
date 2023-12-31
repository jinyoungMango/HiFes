package com.ssafy.hifes.util

import com.ssafy.hifes.data.model.DateDto
import com.ssafy.hifes.data.model.DateTime
import com.ssafy.hifes.data.model.TimeDto
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CommonUtils {
    fun formatDateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy. MM. dd")
        return format.format(date)
    }

    fun formatSqlDateToString(date: java.sql.Date): String {
        val formatter = SimpleDateFormat("yyyy. MM. dd")
        return formatter.format(date)
    }

    fun formatFestivalDateToString(festivalDate: DateDto): String {
        return "${festivalDate.year}. ${festivalDate.month}. ${festivalDate.day}"
    }

    fun formatTimeToString(time: TimeDto): String {
        return "${time.hour}:${time.minute}"
    }

    fun formatDateTimeToString(dateTime: DateTime): String {
        return "${formatFestivalDateToString(dateTime.date)}  ${formatTimeToString(dateTime.time)}"
    }

    fun formatLongToTime(time: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(Date(time))
    }

    fun formatPhoneNumber(number: String): String {
        return "${number.substring(0, 3)}-${number.substring(3, 6)}-${number.substring(6, 10)}"
    }

}