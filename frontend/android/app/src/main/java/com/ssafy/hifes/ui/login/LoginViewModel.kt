package com.ssafy.hifes.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.LoginResponse
import com.ssafy.hifes.data.repository.user.UserRepository
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel_하이페스"
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _msg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _msg

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    fun login(accessToken: String) {
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
                    postValueEvent(0, type)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    private fun postValueEvent(value: Int, type: String) {
        val msgArrayList = arrayOf(
            "Api 오류 : $type 실패했습니다.",
            "서버 오류 : $type 실패했습니다.",
            "알 수 없는 오류 : $type 실패했습니다."
        )

        when (value) {
            0 -> _msg.postValue(Event(msgArrayList[0]))
            1 -> _msg.postValue(Event(msgArrayList[1]))
            2 -> _msg.postValue(Event(msgArrayList[2]))
        }
    }
}