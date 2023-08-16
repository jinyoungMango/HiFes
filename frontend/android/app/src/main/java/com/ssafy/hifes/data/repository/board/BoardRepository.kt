package com.ssafy.hifes.data.repository.board

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.util.network.NetworkResponse
import retrofit2.http.Path

interface BoardRepository {
    suspend fun getPostList(
        @Path("festivalId") festivalId: Int,
        @Path("postType") postType: String
    ): NetworkResponse<List<PostDto>, ErrorResponse>
}