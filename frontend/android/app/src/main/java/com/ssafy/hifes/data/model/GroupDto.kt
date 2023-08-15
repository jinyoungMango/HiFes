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
    val id: Int = -1,
    val groupName: String,
    val groupPic: String?,
    val createdAt: DateTime?,
    val maxPop: Int,
    val content: String,
    val hashtags: List<String>?,
    val numOfPeople: Int?,
    val festivalId: Int
)

data class GroupDetailDto(
    var isJoinedFesGroup: Boolean,
    var isJoinedGroup: Boolean,
    var isLeader: Boolean,
    var groupName: String,
    var groupContent: String,
    var groupMaxPop: Int,
    var groupCreatedAt: String,
    var numOfJoinedPeople: Int,
    var joinedPeople: List<Member>,
    var hashtags: List<String>?,
    var groupPic: String?
)
