package com.ssafy.hifes.data.model

data class StampMissionDto(
    var missionId: Int,
    var festivalId: Int,
    var hostId: Int,
    var missionTitle: String,
    var missionOutline: String,
    var missionLatitude: Double,
    var missionLongitude: Double
)
