package com.ssafy.hifes.data.model

data class TimeTable(
    val programId: Int,
    val programOutline: String,
    val programTitle: String,
    val startTime: DateTime,
    val endTime: DateTime
)