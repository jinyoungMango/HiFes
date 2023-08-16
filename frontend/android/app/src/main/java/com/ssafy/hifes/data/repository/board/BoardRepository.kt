package com.ssafy.hifes.data.repository.board

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.PostDetailDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.util.network.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import retrofit2.http.Path

interface BoardRepository {
    suspend fun getPostList(
        @Path("festivalId") festivalId: Int,
        @Path("postType") postType: String
    ): NetworkResponse<List<PostDto>, ErrorResponse>

    suspend fun getPostDetail(
        @Path("id") id: Int
    ): NetworkResponse<PostDetailDto, ErrorResponse>

    suspend fun writePost(
        @Part("data") data: RequestBody,
        @Part image: MultipartBody.Part?
    ): NetworkResponse<String, ErrorResponse>
}