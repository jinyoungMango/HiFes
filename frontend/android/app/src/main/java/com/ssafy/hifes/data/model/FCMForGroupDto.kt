package com.ssafy.hifes.data.model

data class FCMForGroupDto(
    val groupId: Int,
    val location: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
)