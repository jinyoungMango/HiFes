package com.ssafy.hifes.ui.participatedfest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.ParticipatedFestDto
import com.ssafy.hifes.data.repository.proof.ProofRepository
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ParticipatedFestViewMod"

@HiltViewModel
class ParticipatedFestViewModel @Inject constructor(
    private val repository: ProofRepository
) : ViewModel() {
    private val _errMsgParticipatedFestList = MutableLiveData<Event<String>>()
    val errMsgParticipatedFestList: LiveData<Event<String>> = _errMsgParticipatedFestList
    private val _errMsgStamp = MutableLiveData<Event<String>>()
    val errMsgStamp: LiveData<Event<String>> = _errMsgStamp

    private val userId = AppPreferences.getUserId()
    private val _participatedFestivalList: MutableLiveData<List<ParticipatedFestDto>> =
        MutableLiveData()
    val participatedFestival: LiveData<List<ParticipatedFestDto>> = _participatedFestivalList

    fun getParticipatedFestival() {
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