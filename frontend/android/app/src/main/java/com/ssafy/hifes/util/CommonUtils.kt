package com.ssafy.hifes.util

import com.ssafy.hifes.data.model.DateDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CommonUtils {
    fun formatDateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy. MM. dd")
        return format.format(date)
    }

    fun formatSqlDateToString(date : java.sql.Date): String{
        val formatter = SimpleDateFormat("yyyy. MM. dd")
        return formatter.format(date)
    }

    fun formatFestivalDateToString(festivalDate: DateDto): String {
        return "${festivalDate.year}. ${festivalDate.month}. ${festivalDate.day}"
    }

    fun formatLongToTime(time: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(Date(time))
    }
}