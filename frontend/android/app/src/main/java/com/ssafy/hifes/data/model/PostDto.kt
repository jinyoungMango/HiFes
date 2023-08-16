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
