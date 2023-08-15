package com.ssafy.hifes.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class DateDto(
    val year: Int,
    val month: Int,
    val day: Int
){
    @RequiresApi(Build.VERSION_CODES.O)
    fun toLocalDate(): LocalDate = LocalDate.of(year, month, day)
}