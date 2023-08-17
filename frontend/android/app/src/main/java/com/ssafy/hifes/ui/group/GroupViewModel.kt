package com.ssafy.hifes.ui.group

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.GroupDetailDto
import com.ssafy.hifes.data.model.SharedPicDto
import com.ssafy.hifes.data.repository.group.GroupRepository
import com.ssafy.hifes.ui.group.create.GroupCreateStateType
import com.ssafy.hifes.util.MultipartUtil
import com.ssafy.hifes.util.UriUtil
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

private const val TAG = "GroupViewModel_하이페스"

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val repository: GroupRepository
) : ViewModel() {
    private val gson = Gson()

    private val _msgGroupList = MutableLiveData<Event<String>>()
    val errorMsgGroupList: LiveData<Event<String>> = _msgGroupList

    private val _msgGroupDetail = MutableLiveData<Event<String>>()
    val errorMsgGroupDetail: LiveData<Event<String>> = _msgGroupDetail

    private val _msgGroupImages = MutableLiveData<Event<String>>()
    val errorMsgGroupImages: LiveData<Event<String>> = _msgGroupImages

    private val _msgGroupCreate = MutableLiveData<Event<String>>()
    val errorMsgGroupCreate: LiveData<Event<String>> = _msgGroupCreate

    private val _msgGroupJoin = MutableLiveData<Event<String>>()
    val errorMsgGroupJoin: LiveData<Event<String>> = _msgGroupJoin

    private val _msgGroupSignOut = MutableLiveData<Event<String>>()
    val errorMsgGroupSignOut: LiveData<Event<String>> = _msgGroupSignOut

    private var _groupList: MutableLiveData<List<Group>> = MutableLiveData()
    val groupList: LiveData<List<Group>> = _groupList

    private var _selectedGroup: MutableLiveData<Int> = MutableLiveData()
    val selectedGroup: LiveData<Int> = _selectedGroup

    private var _groupDetailInfo: MutableLiveData<GroupDetailDto> = MutableLiveData()
    val groupDetailInfo: LiveData<GroupDetailDto> = _groupDetailInfo

    private var _groupImages: MutableLiveData<List<SharedPicDto>> = MutableLiveData()
    val groupImages: LiveData<List<SharedPicDto>> = _groupImages

    private var _createStateType: MutableLiveData<GroupCreateStateType> = MutableLiveData()
    val createStateType: LiveData<GroupCreateStateType> = _createStateType

    private var _joinGroupResponse: MutableLiveData<Boolean> = MutableLiveData()
    val joinGroupResponse: LiveData<Boolean> = _joinGroupResponse

    private var _signOutGroupResponse: MutableLiveData<String> = MutableLiveData()
    val signOutGroupResponse: LiveData<String> = _signOutGroupResponse

    private var _uploadPictureStateType: MutableLiveData<GroupCreateStateType> = MutableLiveData()
    val uploadPictureStateType: LiveData<GroupCreateStateType> = _uploadPictureStateType

    fun getAllGroupList() {
        viewModelScope.launch {
            val response = repository.getAllGroupList()
            val type = "그룹 리스트 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _groupList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgGroupList)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgGroupList)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgGroupList)
                }
            }
        }
    }

    fun getFestivalGroupList(selectedFestivalId: Int) {
        Log.d(TAG, "getFestivalGroupList: $selectedFestivalId")
        viewModelScope.launch {
            val response = repository.getFestivalGroupList(selectedFestivalId)
            val type = "그룹 리스트 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _groupList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgGroupList)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgGroupList)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgGroupList)
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
                    postValueEvent(0, type, _msgGroupDetail)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgGroupDetail)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgGroupDetail)
                }
            }
        }
    }

    fun getGroupImages(groupId: Int) {
        viewModelScope.launch {
            val response = repository.getGroupImages(groupId)
            val type = "그룹 이미지 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "getGroupImages: $groupId")
                    _groupImages.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgGroupImages)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgGroupImages)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgGroupImages)
                }
            }
        }
    }

    fun setSelectedGroupId(groupId: Int) {
        _selectedGroup.postValue(groupId)
    }

    fun createGroup(context: Context, uri: Uri, groupDto: Group) {

        val groupImage = MultipartUtil.getImageBody(UriUtil.toFile(context, uri))
        val groupDtoPart =
            gson.toJson(groupDto).toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch {
            val response = repository.createGroup(groupDtoPart, groupImage)
            val type = "그룹 생성에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "createGroup: success")
                    _createStateType.postValue(GroupCreateStateType.SUCCESS)
                }

                is NetworkResponse.ApiError -> {
                    Log.d(TAG, "createGroup: fail1")
                    postValueEvent(0, type, _msgGroupCreate)
                    _createStateType.postValue(GroupCreateStateType.FAIL)
                }

                is NetworkResponse.NetworkError -> {
                    Log.d(TAG, "createGroup: fail2")
                    postValueEvent(1, type, _msgGroupCreate)
                    _createStateType.postValue(GroupCreateStateType.FAIL)
                }

                is NetworkResponse.UnknownError -> {
                    Log.d(TAG, "createGroup: fail3")
                    postValueEvent(2, type, _msgGroupCreate)
                    _createStateType.postValue(GroupCreateStateType.FAIL)
                }
            }
        }
    }

    fun joinGroup(groupId: Int) {
        viewModelScope.launch {
            val response = repository.joinGroup(groupId)
            val type = "그룹 가입에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "joinGroup: ${response.body}")
                    _joinGroupResponse.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgGroupJoin)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgGroupJoin)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgGroupJoin)
                }
            }
        }
    }

    fun signOutGroup(groupId: Int) {
        viewModelScope.launch {
            val response = repository.signOutGroup(groupId)
            val type = "그룹 탈퇴에"
            when (response) {
                is NetworkResponse.Success -> {
                    _signOutGroupResponse.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgGroupSignOut)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgGroupSignOut)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgGroupSignOut)
                }
            }
        }
    }

    fun uploadPicture(context: Context, uri: Uri, groupId: Int) {
        val image = MultipartUtil.getImageBody(UriUtil.toFile(context, uri))

        viewModelScope.launch {
            val response = repository.uploadPicture(image, groupId)
            val type = "이미지 업로드에"
            Log.d(TAG, "uploadPicture: ${uploadPictureStateType.value}")
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "uploadPicture: success")
                    _uploadPictureStateType.postValue(GroupCreateStateType.SUCCESS)
                }

                is NetworkResponse.ApiError -> {
                    Log.d(TAG, "uploadPicture: fail1")
                    postValueEvent(0, type, _msgGroupCreate)
                    _uploadPictureStateType.postValue(GroupCreateStateType.FAIL)
                }

                is NetworkResponse.NetworkError -> {
                    Log.d(TAG, "uploadPicture: fail2")
                    postValueEvent(1, type, _msgGroupCreate)
                    _uploadPictureStateType.postValue(GroupCreateStateType.FAIL)
                }

                is NetworkResponse.UnknownError -> {
                    Log.d(TAG, "uploadPicture: fail3")
                    postValueEvent(2, type, _msgGroupCreate)
                    _uploadPictureStateType.postValue(GroupCreateStateType.FAIL)
                }
            }
        }
    }

    fun initCreateState() {
        _uploadPictureStateType.postValue(GroupCreateStateType.LOADING)
    }

    fun initUploadPictureState() {
        _createStateType.postValue(GroupCreateStateType.LOADING)
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
