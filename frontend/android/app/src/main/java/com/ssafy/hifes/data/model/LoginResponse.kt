package com.ssafy.hifes.data.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val result: Boolean,
    val id: String,
    val nickname: String
)

data class NormalUserSignUpDto(
    var nickname: String,
    var accessToken: String
)