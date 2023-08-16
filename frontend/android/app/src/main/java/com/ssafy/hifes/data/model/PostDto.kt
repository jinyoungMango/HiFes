package com.ssafy.hifes.data.model

data class PostDto(
    var id: Int,
    var createdBy: Int,
    var writer: String,
    var title: String,
    var postType: String,
    var organizedFestivalId: Int,
    var views: Int,
    var createdAt: DateTime,
    var content: String="",
    var isHidden: Boolean?,
    var hideReason: String?,
    var imagePath: String?,
    var rating: Float?,
    var commentsCount: Int = 0
)

data class PostDetailDto(
    var id: Int,
    var createdBy: Int,
    var writer: String,
    var title: String,
    var content: String,
    var postType: String,
    var isHidden: Boolean?,
    var imagePath: String?,
    var organizedFestivalId: Int,
    var views: Int,
    var rating: Float?,
    var createdAt: DateTime,
    var topLevelComments: List<CommentDto>
)
