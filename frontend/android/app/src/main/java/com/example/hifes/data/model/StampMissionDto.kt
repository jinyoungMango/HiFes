package com.example.hifes.data.model

data class StampMissionDto(
    var missionId: Int,
    var festivalId: Int,
    var hostId: Int,
    var missionTitle: String,
    var missionOutline: String,
    var missionLatitude: Float,
    var missionLongitude: Float
)
