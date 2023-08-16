package com.ssafy.hifes.data.repository.board

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
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

}