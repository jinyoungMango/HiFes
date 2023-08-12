package com.ssafy.hifes.data.model

data class OrganizedFestivalDto(
    var fesTitle: String,
    var fesOutline: String,
    var fesAddress: String,
    var fesPosterPath: String,
    var fesStartDate: FestivalDate,
    var fesEndDate: FestivalDate,
    var fesLatitude: Double,
    var fesLongitude: Double,
    var festivalId: Int
)

data class FestivalDate(
    val year : Int,
    val month : Int,
    val day : Int
)
