package com.ssafy.hifes.util

import java.text.SimpleDateFormat
import java.util.Date

object CommonUtils {
    fun formatDateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy. MM. dd")
        return format.format(date)
    }
}