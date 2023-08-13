package com.ssafy.hifes.data.repository.group

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.GroupDetailDto
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GroupRepository {

    override suspend fun getAllGroupList(): NetworkResponse<List<Group>, ErrorResponse> {
        return apiService.getAllGroupList()
    }

    override suspend fun getFestivalGroupList(festivalId: Int): NetworkResponse<List<Group>, ErrorResponse> {
        return apiService.getFestivalGroupList(festivalId)
    }

    override suspend fun getGroupDetailInfo(groupId: Int): NetworkResponse<GroupDetailDto, ErrorResponse> {
        return apiService.getGroupDetailInfo(groupId)
    }

}