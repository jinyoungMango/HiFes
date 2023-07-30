package com.example.hifes.data.model

import java.sql.Date


data class ParticipatedFestDto(
    var normalUserId: Int,
    var festivalId: Int,
    var hostId: Int,
    var participatedFesImg: String,
    var isCompleted: Boolean,
    var completeTime: Date
)
