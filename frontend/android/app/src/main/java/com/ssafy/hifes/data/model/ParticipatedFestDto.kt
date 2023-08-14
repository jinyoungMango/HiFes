package com.ssafy.hifes.data.model


data class ParticipatedFestDto(
    var festivalId: Int,
    var normalUserId: Int,
    var isCompleted: Boolean,
    var fesTitle: String,
    var countMission: Int,
    var participateTime: CreatedAtDto
)
