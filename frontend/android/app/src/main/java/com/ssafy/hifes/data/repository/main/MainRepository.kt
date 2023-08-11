package com.ssafy.hifes.data.repository.main

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.util.network.NetworkResponse
import retrofit2.http.Path

interface MainRepository {

    suspend fun randomFestivals() : NetworkResponse<List<OrganizedFestivalDto>, ErrorResponse>

    suspend fun nearbyFestivals(
        @Path("userLatitude") userLatitude: Double,
        @Path("userLongitude") userLongitude: Double,
    ): NetworkResponse<List<OrganizedFestivalDto>, ErrorResponse>
}