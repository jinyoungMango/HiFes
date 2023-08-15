package com.ssafy.hifes.data.repository.proof

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.ParticipatedFestDto
import com.ssafy.hifes.util.network.NetworkResponse
import retrofit2.http.POST
import retrofit2.http.Path

interface ProofRepository {
    suspend fun participateFestival(
        @Path("normalUserId") normalUserId: String,
        @Path("festivalId") festivalId: Int
    ): NetworkResponse<Boolean, ErrorResponse>

    suspend fun completeMission(
        @Path("normalUserID") normalUserId: String,
        @Path("missionId") missionId: Int
    ): NetworkResponse<Boolean, ErrorResponse>
}