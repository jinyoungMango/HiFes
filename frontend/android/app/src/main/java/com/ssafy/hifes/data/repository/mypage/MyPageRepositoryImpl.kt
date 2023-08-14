package com.ssafy.hifes.data.repository.mypage

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.ParticipatedFestDto
import com.ssafy.hifes.data.model.StampListDto
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MyPageRepository {
    override suspend fun getParticipateFestival(
        normalUserId: String
    ): NetworkResponse<List<ParticipatedFestDto>, ErrorResponse> {
        return apiService.getParticipateFestival(normalUserId)
    }

    override suspend fun getParticipatedStampList(
        normalUserId: String,
        festivalId: Int
    ): NetworkResponse<StampListDto, ErrorResponse> {
        return apiService.getParticipatedStampList(normalUserId, festivalId)
    }
}