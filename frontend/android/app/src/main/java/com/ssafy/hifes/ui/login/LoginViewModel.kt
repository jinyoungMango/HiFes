package com.ssafy.hifes.ui.login

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.FcmTokenDto
import com.ssafy.hifes.data.model.LoginResponse
import com.ssafy.hifes.data.model.NormalUserSignUpDto
import com.ssafy.hifes.data.repository.user.UserRepository
import com.ssafy.hifes.util.MultipartUtil
import com.ssafy.hifes.util.UriUtil
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


private const val TAG = "LoginViewModel_하이페스"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val gson = Gson()

    private val _msg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _msg

    private val _msgFcmSave = MutableLiveData<Event<String>>()
    val msgFcmSave: LiveData<Event<String>> = _msgFcmSave

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private var kakaoAccessToken = ""

    fun login(accessToken: String) {
        kakaoAccessToken = accessToken
        viewModelScope.launch {
            val response = repository.login(accessToken)
            val type = "token 정보 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "login: $response")
                    _loginResponse.postValue(response.body)
                    AppPreferences.initLoginInfo(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msg)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msg)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msg)
                }
            }
        }
    }

    fun signUp(context: Context, uri: Uri?, nickname: String) {
        val file = UriUtil.toFile(context, uri!!)
        val profilePic = MultipartUtil.getImageBody(file)

        val normalUserSignUpDto = NormalUserSignUpDto(nickname, kakaoAccessToken)
        viewModelScope.launch {
            val requestBody =
                gson.toJson(normalUserSignUpDto)
                    .toRequestBody("application/json".toMediaTypeOrNull())
            val response = repository.signUp(requestBody, profilePic)
            Log.d(TAG, "signUp: $response")
            val type = "token 정보 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "signUp: $response")
                    _loginResponse.postValue(response.body)
                    AppPreferences.initLoginInfo(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msg)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msg)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msg)
                }
            }
        }
    }

    fun saveFcmToken() {
        viewModelScope.launch {
            val type = "알림 토큰 저장에"
            var fcmToken = AppPreferences.getFcmToken()

            if (fcmToken == "") {
                postValueEvent(2, type, _msgFcmSave)
            } else {
                val response = repository.saveFcmToken(FcmTokenDto(fcmToken))

                when (response) {
                    is NetworkResponse.Success -> {

                    }

                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type, _msgFcmSave)
                    }

                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type, _msgFcmSave)
                    }

                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type, _msgFcmSave)
                    }
                }
            }

        }
    }

    private fun postValueEvent(
        value: Int,
        type: String,
        mutableLiveData: MutableLiveData<Event<String>>
    ) {
        val msgArrayList = arrayOf(
            "Api 오류 : $type 실패했습니다.",
            "서버 오류 : $type 실패했습니다.",
            "알 수 없는 오류 : $type 실패했습니다."
        )

        when (value) {
            0 -> mutableLiveData.postValue(Event(msgArrayList[0]))
            1 -> mutableLiveData.postValue(Event(msgArrayList[1]))
            2 -> mutableLiveData.postValue(Event(msgArrayList[2]))
        }
    }
}