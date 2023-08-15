package com.ssafy.hifes.data.repository.proof

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import javax.inject.Inject

class ProofRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProofRepository {
    override suspend fun participateFestival(
        normalUserId: String,
        festivalId: Int
    ): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.participateFestival(normalUserId, festivalId)
    }

    override suspend fun completeMission(
        normalUserId: String,
        missionId: Int
    ): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.completeMission(normalUserId, missionId)
    }
}