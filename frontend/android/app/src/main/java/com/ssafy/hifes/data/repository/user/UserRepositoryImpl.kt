package com.ssafy.hifes.data.repository.user

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.LoginResponse
import com.ssafy.hifes.data.remote.ApiService
import com.ssafy.hifes.util.network.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun login(accessToken: String): NetworkResponse<LoginResponse, ErrorResponse> {
        return apiService.login(accessToken)
    }

    override suspend fun signUp(
        normalUserSignUpDto: RequestBody,
        image: MultipartBody.Part
    ): NetworkResponse<LoginResponse, ErrorResponse> {
        return apiService.signUp(normalUserSignUpDto, image)
    }


}