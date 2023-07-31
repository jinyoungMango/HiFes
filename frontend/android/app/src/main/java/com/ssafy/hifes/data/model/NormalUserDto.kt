package com.ssafy.hifes.data.model

data class NormalUserDto(
    var normalUserId: Int,
    var id: String,
    var password : String,
    var name: String,
    var phoneNo: String,
    var nickname: String,
    var profileImg: String
)
