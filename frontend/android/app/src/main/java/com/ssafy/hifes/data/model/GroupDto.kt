package com.ssafy.hifes.data.model

import java.sql.Date

data class GroupDto(
    var groupId: Int,
    var groupName: String,
    var groupPic: String,
    var createdAt: Date,
    var maxPop: Int,
    var content: String,
    var getterLatitude: Float,
    var getterLongitude: Float,
    var groupPassword: Int,
)
