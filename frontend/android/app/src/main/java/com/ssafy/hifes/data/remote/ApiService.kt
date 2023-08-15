package com.ssafy.hifes.data.remote

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.GroupDetailDto
import com.ssafy.hifes.data.model.LoginResponse
import com.ssafy.hifes.data.model.MarkerDto
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.data.model.ParticipatedFestDto
import com.ssafy.hifes.data.model.SharedPicDto
import com.ssafy.hifes.data.model.StampListDto
import com.ssafy.hifes.data.model.TimeTable
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

    // Group
    @GET("group/list")
    suspend fun getAllGroupList(): NetworkResponse<List<Group>, ErrorResponse>

    @GET("group/list/fes/{festivalId}")
    suspend fun getFestivalGroupList(@Path("festivalId") festivalId: Int): NetworkResponse<List<Group>, ErrorResponse>

    @GET("group/detail/{id}")
    suspend fun getGroupDetailInfo(@Path("id") groupId: Int): NetworkResponse<GroupDetailDto, ErrorResponse>

    @GET("group/{groupId}/picture/")
    suspend fun getGroupImages(@Path("groupId") groupId: Int): NetworkResponse<List<SharedPicDto>, ErrorResponse>

    @Multipart
    @POST("group/create")
    suspend fun createGroup(
        @Part("groupCreateDto") groupCreateDto: RequestBody,
        @Part image: MultipartBody.Part
    ): NetworkResponse<String, ErrorResponse>

    // Proof
    @POST("{normalUserId}/participate-festival/{festivalId}") //티켓 발급(행사 참여 인증)
    suspend fun participateFestival(
        @Path("normalUserId") normalUserId: String,
        @Path("festivalId") festivalId: Int
    ): NetworkResponse<Boolean, ErrorResponse>

    @POST("{normalUserId}/complete-mission/{missionId}")
    suspend fun completeMission(
        @Path("normalUserId") normalUserId: String,
        @Path("missionId") missionId: Int
    ): NetworkResponse<Boolean, ErrorResponse>

    // MyPage
    @GET("{normalUserId}/participate-festivals")//티켓 조회
    suspend fun getParticipateFestival(
        @Path("normalUserId") normalUserId: String
    ): NetworkResponse<List<ParticipatedFestDto>, ErrorResponse>

    @GET("{normalUserId}/{festivalId}/complete-missions")
    suspend fun getParticipatedStampList(
        @Path("normalUserId") normalUserId: String,
        @Path("festivalId") festivalId: Int
    ): NetworkResponse<StampListDto, ErrorResponse>

    // Festival
    @GET("festival/{festivalId}/festivalTables")
    suspend fun getFestivalTimeTable(@Path("festivalId") festivalId: Int): NetworkResponse<List<TimeTable>, ErrorResponse>

    @GET("search-festival/")
    suspend fun searchFestivalList(@Query("keyword") keyword: String): NetworkResponse<List<OrganizedFestivalDto>, ErrorResponse>
}