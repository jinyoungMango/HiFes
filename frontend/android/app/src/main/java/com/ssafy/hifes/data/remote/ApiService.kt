package com.ssafy.hifes.data.remote

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.LoginResponse
import com.ssafy.hifes.data.model.MarkerDto
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.util.network.NetworkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("normal/login")
    suspend fun login(@Query("accessToken") accessToken: String): NetworkResponse<LoginResponse, ErrorResponse>

    @Multipart
    @POST("normal/signUp")
    suspend fun signUp(
        @Part("normalUserSignUpDto") normalUserSignUpDto: RequestBody,
        @Part image: MultipartBody.Part
    ): NetworkResponse<LoginResponse, ErrorResponse>

    @GET("/api/randomFestivals")
    suspend fun randomFestivals(): NetworkResponse<List<OrganizedFestivalDto>, ErrorResponse>

    @GET("/api/nearby-festivals/{userLatitude}/{userLongitude}")
    suspend fun nearbyFestivals(
        @Path("userLatitude") userLatitude: Double,
        @Path("userLongitude") userLongitude: Double,
    ): NetworkResponse<List<OrganizedFestivalDto>, ErrorResponse>

    @GET("/api/festival/{festivalId}")
    suspend fun getFestivalInfo(@Path("festivalId") festivalId: Int): NetworkResponse<OrganizedFestivalDto, ErrorResponse>

    @GET("/api/festival/{festivalId}/markers")
    suspend fun getMarkerList(@Path("festivalId") festivalId: Int): NetworkResponse<List<MarkerDto>, ErrorResponse>

    //Group
    @GET("group/list")
    suspend fun getAllGroupList(): NetworkResponse<List<Group>, ErrorResponse>
}