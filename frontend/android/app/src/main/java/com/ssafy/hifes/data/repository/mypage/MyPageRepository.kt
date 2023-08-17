package com.ssafy.hifes.data.repository.mypage

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.ParticipatedFestDto
import com.ssafy.hifes.data.model.StampListDto
import com.ssafy.hifes.data.model.UserInfoDto
import com.ssafy.hifes.util.network.NetworkResponse
import retrofit2.http.Path

interface MyPageRepository {
    suspend fun getParticipateFestival(
        @Path("normalUserId") normalUserId: String
    ): NetworkResponse<List<ParticipatedFestDto>, ErrorResponse>

    suspend fun getParticipatedStampList(
        @Path("normalUserId") normalUserId: String,
        @Path("festivalId") festivalId: Int
    ): NetworkResponse<StampListDto, ErrorResponse>

    suspend fun getUserInfo(): NetworkResponse<UserInfoDto, ErrorResponse>

}