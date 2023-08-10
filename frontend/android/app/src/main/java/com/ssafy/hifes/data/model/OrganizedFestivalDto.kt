package com.ssafy.hifes.data.model

import java.sql.Date

data class OrganizedFestivalDto(
    var festivalId: Int,
    var hostId: Int,
    var fesTitle: String,
    var fesPosterPath: String,
    var fesStartDate: Date,
    var fesEndDate: Date,
    var fesOutline: String,
    var fesAddress: String,
    var fesLatitude: Double,
    var fesLongitude: Double
) {
    constructor() : this(0, 0, "", "", Date(java.util.Date().time), Date(java.util.Date().time),
        "", "", 0.0, 0.0)
}
