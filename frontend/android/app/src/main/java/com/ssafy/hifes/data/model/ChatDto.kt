package com.ssafy.hifes.data.model

data class ChatData(
    val id: String?,
    val messages: List<MessageData>
)
data class UserData(
    val id: String?,
    val nickname: String?
)
data class MessageData(
    val content: String,
    val id: UserData,
    val time: Long
)