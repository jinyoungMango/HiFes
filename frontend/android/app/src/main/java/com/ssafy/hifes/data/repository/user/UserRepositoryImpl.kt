package com.ssafy.hifes.data.repository.user

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.LoginResponse
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun login(accessToken: String): NetworkResponse<LoginResponse, ErrorResponse> {
        return apiService.login(accessToken)
    }
}