package com.ssafy.hifes.data.model

data class ARItemDto(
    var itemId: Int,
    var festivalId: Int,
    var hostId: Int,
    var ARLatitude: Double,
    var ARLongitude: Double,
    var ARImage: String,
    var giftInfo: String
)
