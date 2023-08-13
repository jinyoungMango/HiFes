package com.ssafy.hifes.data.repository.festival

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.MarkerDto
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import javax.inject.Inject

class FestivalRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FestivalRepository {
    override suspend fun getMarkerList(festivalId: Int): NetworkResponse<List<MarkerDto>, ErrorResponse> {
        return apiService.getMarkerList(festivalId)
    }

}