package com.ssafy.hifes.util

import com.ssafy.hifes.data.model.DateDto
import java.text.SimpleDateFormat
import java.util.Date

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
}