package com.ssafy.hifes.data.model

data class ChatData(
    val id: String?,
    val messages: List<MessageData>
)
data class UserData(
    val nickname: String?,
    val id: String?
)
data class MessageData(
    val content: String,
    val userData: UserData,
    val time: Long
)