package com.ssafy.hifes.data.model

import java.sql.Date

data class CommentDto(
    var id: Int,
    var content: String,
    var createdBy: Int,
    var createdAt: DateTime,
    var postId: Int?,
    var childComments: List<CommentDto>,
    var writer: String,
)
