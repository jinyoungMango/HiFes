package com.ssafy.hifes.data.repository.group

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.GroupDetailDto
import com.ssafy.hifes.data.model.SharedPicDto
import com.ssafy.hifes.util.network.NetworkResponse

interface GroupRepository {
    suspend fun getAllGroupList(): NetworkResponse<List<Group>, ErrorResponse>
    suspend fun getFestivalGroupList(festivalId: Int): NetworkResponse<List<Group>, ErrorResponse>
    suspend fun getGroupDetailInfo(groupId: Int): NetworkResponse<GroupDetailDto, ErrorResponse>
    suspend fun getGroupImages(groupId: Int): NetworkResponse<List<SharedPicDto>, ErrorResponse>
}