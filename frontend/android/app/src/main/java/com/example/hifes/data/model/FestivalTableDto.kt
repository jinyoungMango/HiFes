package com.example.hifes.data.model

import java.sql.Date

data class FestivalTableDto(
    var programId: Int,
    var festivalId: Int,
    var hostId: Int,
    var programTitle: String,
    var programOutline: String,
    var startTime: Date,
    var endTime: Date
)
