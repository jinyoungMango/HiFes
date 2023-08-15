package com.ssafy.hifes.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

data class TimeDto(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val nano: Int
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun toLocalTime(): LocalTime = LocalTime.of(hour, minute, second, nano)
}