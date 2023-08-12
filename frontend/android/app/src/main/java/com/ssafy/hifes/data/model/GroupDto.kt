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
    val id: Int,
    val groupName: String,
    val groupPic: String,
    val createdAt: CreatedAtDto,
    val maxPop: Int,
    val content: String,
    val hashtags: List<String>?,
    val numOfPeople: Int,
    val festivalId : Int
)

