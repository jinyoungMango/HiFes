package com.example.hifes.data.model

data class ARItemDto(
    var itemId: Int,
    var festivalId: Int,
    var hostId: Int,
    var ARLatitude: Float,
    var ARLongitude: Float,
    var ARImage: String,
    var giftInfo: String
)
