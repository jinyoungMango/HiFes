package com.ssafy.hifes.data.model

import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    var email: String,
    var name: String,
    var nickname: String,
    @SerializedName("ProfilePic")
    var profilePic: String
)