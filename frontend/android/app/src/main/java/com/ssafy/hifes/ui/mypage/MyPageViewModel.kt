package com.ssafy.hifes.ui.mypage

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.ParticipatedFestDto
import com.ssafy.hifes.data.model.StampListDto
import com.ssafy.hifes.data.model.UserInfoDto
import com.ssafy.hifes.data.repository.mypage.MyPageRepository
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MyPageViewModel"

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val repository: MyPageRepository
) : ViewModel() {
    private val _errMsgParticipatedFestList = MutableLiveData<Event<String>>()
    val errMsgParticipatedFestList: LiveData<Event<String>> = _errMsgParticipatedFestList

    private val _errMsgParticipatedStamp = MutableLiveData<Event<String>>()
    val errMsgParticipatedStamp: LiveData<Event<String>> = _errMsgParticipatedStamp

    private val _errMsgUserInfo = MutableLiveData<Event<String>>()
    val errMsgUserInfo: LiveData<Event<String>> = _errMsgUserInfo

    private val _participatedFestivalList: MutableLiveData<List<ParticipatedFestDto>> =
        MutableLiveData()
    val participatedFestival: LiveData<List<ParticipatedFestDto>> = _participatedFestivalList

    private val _participatedStamps: MutableLiveData<StampListDto> = MutableLiveData()
    val participatedStamps: LiveData<StampListDto> = _participatedStamps

    private val _userInfo: MutableLiveData<UserInfoDto> = MutableLiveData()
    val userInfo: LiveData<UserInfoDto> = _userInfo

    fun getParticipatedFestival(userId: String?) {
        viewModelScope.launch {
            val type = "티켓 정보 조회에"
            if (userId != null) {
                val response = repository.getParticipateFestival(userId)
                when (response) {
                    is NetworkResponse.Success -> {
                        _participatedFestivalList.postValue(response.body)
                    }

                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type, _errMsgParticipatedFestList)
                    }

                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type, _errMsgParticipatedFestList)
                    }

                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type, _errMsgParticipatedFestList)
                    }
                }
            } else {
                postValueEvent(2, type, _errMsgParticipatedFestList)
            }


        }
    }

    fun getParticipatedStamp(festivalId: Int, userId: String?) {
        viewModelScope.launch {
            val type = "스탬프 정보 조회에"
            if (userId != null) {
                val response = repository.getParticipatedStampList(userId, festivalId)
                when (response) {
                    is NetworkResponse.Success -> {
                        _participatedStamps.postValue(response.body)
                    }

                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type, _errMsgParticipatedStamp)
                    }

                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type, _errMsgParticipatedStamp)
                    }

                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type, _errMsgParticipatedStamp)
                    }
                }
            } else {
                postValueEvent(2, type, _errMsgParticipatedStamp)

            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            val type = "회원 정보 조회에"

            val response = repository.getUserInfo()
            when (response) {
                is NetworkResponse.Success -> {
                    _userInfo.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _errMsgUserInfo)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _errMsgUserInfo)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _errMsgUserInfo)
                }
            }

        }
    }

    fun logout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(ContentValues.TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                AppPreferences.removeAccessToken()
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