package com.ssafy.hifes.data.repository.group

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.GroupDetailDto
import com.ssafy.hifes.data.model.SharedPicDto
import com.ssafy.hifes.util.network.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupRepository {
    suspend fun getAllGroupList(): NetworkResponse<List<Group>, ErrorResponse>
    suspend fun getFestivalGroupList(festivalId: Int): NetworkResponse<List<Group>, ErrorResponse>
    suspend fun getGroupDetailInfo(groupId: Int): NetworkResponse<GroupDetailDto, ErrorResponse>
    suspend fun getGroupImages(groupId: Int): NetworkResponse<List<SharedPicDto>, ErrorResponse>
    suspend fun createGroup(
        @Part("groupCreateDto") groupCreateDto: RequestBody,
        @Part image: MultipartBody.Part
    ): NetworkResponse<String, ErrorResponse>

    suspend fun joinGroup(@Path("groupId") groupId: Int): NetworkResponse<Boolean, ErrorResponse>

    suspend fun signOutGroup(@Path("groupId") groupId: Int): NetworkResponse<String, ErrorResponse>

    suspend fun uploadPicture(
        @Part image: MultipartBody.Part,
        @Query("groupId") groupId: Int
    ): NetworkResponse<String, ErrorResponse>
}