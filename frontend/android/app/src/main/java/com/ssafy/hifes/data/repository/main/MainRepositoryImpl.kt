package com.ssafy.hifes.data.repository.main

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {

    override suspend fun randomFestivals(): NetworkResponse<List<OrganizedFestivalDto>, ErrorResponse> {
        return apiService.randomFestivals()
    }

    override suspend fun nearbyFestivals(
        userLatitude: Double,
        userLongitude: Double
    ): NetworkResponse<List<OrganizedFestivalDto>, ErrorResponse> {
        return apiService.nearbyFestivals(userLatitude, userLongitude)
    }

    override suspend fun getFestivalInfo(festivalId: Int): NetworkResponse<OrganizedFestivalDto, ErrorResponse> {
        return apiService.getFestivalInfo(festivalId)
    }

}