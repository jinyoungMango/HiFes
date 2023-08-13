package com.ssafy.hifes.ui.group

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.GroupDetailDto
import com.ssafy.hifes.data.repository.group.GroupRepository
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GroupViewModel"

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val repository: GroupRepository
) : ViewModel() {
    private val _msg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _msg

    private var _groupList: MutableLiveData<List<Group>> = MutableLiveData()
    val groupList: LiveData<List<Group>> = _groupList

    private var _selectedGroup: MutableLiveData<Int> = MutableLiveData()
    val selectedGroup: LiveData<Int> = _selectedGroup

    private var _groupDetailInfo: MutableLiveData<GroupDetailDto> = MutableLiveData()
    val groupDetailInfo: LiveData<GroupDetailDto> = _groupDetailInfo

    fun getAllGroupList() {
        viewModelScope.launch {
            val response = repository.getAllGroupList()
            val type = "그룹 리스트 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _groupList.postValue(response.body)
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

    fun getFestivalGroupList(festivalId: Int) {
        Log.d(TAG, "getFestivalGroupList: $festivalId")
        viewModelScope.launch {
            val response = repository.getFestivalGroupList(festivalId)
            val type = "그룹 리스트 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _groupList.postValue(response.body)
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

    fun getGroupDetail(groupId: Int) {
        viewModelScope.launch {
            val response = repository.getGroupDetailInfo(groupId)
            val type = "그룹 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _groupDetailInfo.postValue(response.body)
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

    fun setSelectedGroupId(groupId: Int) {
        _selectedGroup.postValue(groupId)
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