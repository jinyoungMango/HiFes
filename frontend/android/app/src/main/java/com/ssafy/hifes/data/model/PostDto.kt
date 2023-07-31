package com.ssafy.hifes.data.model

import java.sql.Date

data class PostDto(
    var postId: Int,
    var festivalId: Int,
    var hostId: Int,
    var normalUserId: Int,
    var title: String,
    var content: String,
    var postType: String,
    var hidden: Boolean,
    var hideReason: String,
    var createdBy: String,
    var createdAt: Date,
    var updatedAt: Date,
    var seenTimes: Int,
    var picture: String,
    var rating: Float
)
