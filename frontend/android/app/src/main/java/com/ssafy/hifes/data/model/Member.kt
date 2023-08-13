package com.ssafy.hifes.data.model

data class Member (
    var userId : Long,
    var userNickName : String,
    var userProfilePic : String,
    var isLeader : Boolean
)