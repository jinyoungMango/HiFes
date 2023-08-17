package com.ssafy.hifes.data.repository.festival

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.FCMForGroupDto
import com.ssafy.hifes.data.model.MarkerDto
import com.ssafy.hifes.data.model.TimeTable
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import javax.inject.Inject

class FestivalRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FestivalRepository {
    override suspend fun getMarkerList(festivalId: Int): NetworkResponse<List<MarkerDto>, ErrorResponse> {
        return apiService.getMarkerList(festivalId)
    }

    override suspend fun getFestivalTimeTable(festivalId: Int): NetworkResponse<List<TimeTable>, ErrorResponse> {
        return apiService.getFestivalTimeTable(festivalId)
    }

    override suspend fun callGroupNotification(fcmForGroupDto: FCMForGroupDto): NetworkResponse<String, ErrorResponse> {
        return apiService.callGroupNotification(fcmForGroupDto)
    }
    override suspend fun subscribeFestivalNotice(festivalId: Int): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.subscribeFestivalNotice(festivalId)
    }

}