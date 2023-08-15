package com.ssafy.hifes.data.model

data class OrganizedFestivalDto(
    var festivalId: Int,
    var fesTitle: String,
    var fesOutline: String,
    var fesAddress: String,
    var fesPosterPath: String,
    var fesStartDate: DateDto,
    var fesEndDate: DateDto,
    var fesLatitude: Double,
    var fesLongitude: Double,
    var avgRating : Double,
    var countGroups : Int,
    var hostName : String,
    var hostPhoneNo : String
)
