package com.ssafy.hifes.data.repository.user

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.LoginResponse
import com.ssafy.hifes.util.network.NetworkResponse
import retrofit2.http.Query

interface UserRepository {

    suspend fun login(@Query("accessToken") accessToken: String) : NetworkResponse<LoginResponse, ErrorResponse>
}