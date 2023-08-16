package com.ssafy.hifes.data.repository.group

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.GroupDetailDto
import com.ssafy.hifes.data.model.SharedPicDto
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    override suspend fun getGroupImages(groupId: Int): NetworkResponse<List<SharedPicDto>, ErrorResponse> {
        return apiService.getGroupImages(groupId)
    }

    override suspend fun createGroup(
        groupCreateDto: RequestBody,
        image: MultipartBody.Part
    ): NetworkResponse<String, ErrorResponse> {
        return apiService.createGroup(groupCreateDto, image)
    }

    override suspend fun joinGroup(groupId: Int): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.joinGroup(groupId)
    }

    override suspend fun signOutGroup(groupId: Int): NetworkResponse<String, ErrorResponse> {
        return apiService.signOutGroup(groupId)
    }

    override suspend fun uploadPicture(
        image: MultipartBody.Part,
        groupId: Int
    ): NetworkResponse<String, ErrorResponse> {
        return apiService.uploadPicture(image, groupId)
    }

}