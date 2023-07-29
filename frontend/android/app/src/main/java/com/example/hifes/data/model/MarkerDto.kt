package com.example.hifes.data.model

data class MarkerDto(
    var markerId: Int,
    var festivalId: Int,
    var hostId: Int,
    var boothName: String,
    var boothLatitude: Float,
    var boothLongitude: Float,
    var showingImg: String,
    var description: String,
    var boothNo: Int,
    var markerColor: String
)
