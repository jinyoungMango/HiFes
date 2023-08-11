package com.ssafy.hifes.data.model

import java.sql.Date

data class CommentDto(
    var commentId: Int,
    var normalUserId: Int,
    var postId: Int,
    var festivalId: Int,
    var hostId: Int,
    var comment: String,
    var createdAt: Date,
    var createdBy: String,
    var updatedAt: Date,
    var depth: Boolean
)
