package com.ssafy.hifes.data.model

data class OrganizedFestivalDto(
    var fesTitle: String,
    var fesOutline: String,
    var fesAddress: String,
    var fesPosterPath: String,
    var fesStartDate: DateDto,
    var fesEndDate: DateDto,
    var fesLatitude: Double,
    var fesLongitude: Double,
    var festivalId: Int
)
