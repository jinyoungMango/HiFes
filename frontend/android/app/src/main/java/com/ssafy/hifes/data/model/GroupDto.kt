package com.ssafy.hifes.data.model

import java.sql.Date

data class GroupDto(
    var groupId: Int,
    var groupName: String,
    var groupPic: String,
    var createdAt: Date,
    var maxPop: Int,
    var content: String,
    var getterLatitude: Double,
    var getterLongitude: Double,
    var groupPassword: Int,
)

data class Group(
    val url: String,
    val title: String,
    val content: String,
    val hashtag: List<String>,
    val currNum: Int,
    val maxNum: Int
)
