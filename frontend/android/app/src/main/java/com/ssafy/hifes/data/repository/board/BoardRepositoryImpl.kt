package com.ssafy.hifes.data.repository.board

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.PostDetailDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class BoardRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BoardRepository {

    override suspend fun getPostList(
        festivalId: Int,
        postType: String
    ): NetworkResponse<List<PostDto>, ErrorResponse> {
        return apiService.getPostList(festivalId, postType)
    }

    override suspend fun getPostDetail(id: Int): NetworkResponse<PostDetailDto, ErrorResponse> {
        return apiService.getPostDetail(id)
    }

    override suspend fun writePost(
        data: RequestBody,
        image: MultipartBody.Part?
    ): NetworkResponse<String, ErrorResponse> {
        return apiService.writePost(data, image)
    }

}