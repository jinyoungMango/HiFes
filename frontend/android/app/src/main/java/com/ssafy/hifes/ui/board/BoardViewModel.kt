package com.ssafy.hifes.ui.board

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.PostDetailDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.data.model.PostWriteDto
import com.ssafy.hifes.data.repository.board.BoardRepository
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.board.write.PostWriteStateType
import com.ssafy.hifes.util.MultipartUtil
import com.ssafy.hifes.util.UriUtil
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

private const val TAG = "BoardViewModel"

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val repository: BoardRepository
) : ViewModel() {
    private val gson = Gson()

    private val _msgPostList = MutableLiveData<Event<String>>()
    val errorMsgPostList: LiveData<Event<String>> = _msgPostList

    private val _msgPostDetail = MutableLiveData<Event<String>>()
    val msgPostDetail: LiveData<Event<String>> = _msgPostDetail

    private val _msgPostWrite = MutableLiveData<Event<String>>()
    val msgPostWrite: LiveData<Event<String>> = _msgPostWrite

    private var _postList: MutableLiveData<List<PostDto>> = MutableLiveData()
    val postList: LiveData<List<PostDto>> = _postList

    private var _selectedPost: MutableLiveData<PostDetailDto> = MutableLiveData()
    val selectedPost: LiveData<PostDetailDto> = _selectedPost

    var selectedPostType = "notice"

    private var _boardType: MutableLiveData<PostType> = MutableLiveData()
    val boardType: LiveData<PostType> = _boardType

    private var _postWriteStateType: MutableLiveData<PostWriteStateType> = MutableLiveData()
    val postWriteStateType: LiveData<PostWriteStateType> = _postWriteStateType

    fun getNotificationPostList(selectedFestivalId: Int) {

        viewModelScope.launch {
            val response = repository.getPostList(selectedFestivalId, PostType.NOTIFICATION.label)
            val type = "게시글 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _postList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgPostList)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgPostList)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgPostList)
                }
            }
        }

    }

    fun getAskPostList(selectedFestivalId: Int) {
        viewModelScope.launch {
            val response = repository.getPostList(selectedFestivalId, PostType.ASK.label)
            val type = "게시글 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _postList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgPostList)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgPostList)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgPostList)
                }
            }
        }
    }

    fun getFreePostList(selectedFestivalId: Int) {
        viewModelScope.launch {
            val response = repository.getPostList(selectedFestivalId, PostType.FREE.label)
            val type = "게시글 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _postList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgPostList)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgPostList)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgPostList)
                }
            }
        }
    }

    fun getReviewPostList(selectedFestivalId: Int) {
        viewModelScope.launch {
            val response = repository.getPostList(selectedFestivalId, PostType.REVIEW.label)
            val type = "게시글 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _postList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgPostList)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgPostList)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgPostList)
                }
            }
        }
    }

    fun initBoardType(postType: PostType) {
        _boardType.postValue(postType)
    }

    fun getPostDetail(postData: PostDto) {
        viewModelScope.launch {
            val response = repository.getPostDetail(postData.id)
            val type = "게시글 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    _selectedPost.postValue(response.body)
                    selectedPostType = postData.postType
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type, _msgPostDetail)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type, _msgPostDetail)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type, _msgPostDetail)
                }
            }
        }
    }

    fun postWrite(context: Context, postData: PostWriteDto, uri: Uri?) {
        Log.d(TAG, "postWrite: 타입 ${boardType}")
        Log.d(TAG, "postWrite: 작성할 데이터 ${postData}")
        when (postData.postType) {
            PostType.ASK.label -> {
                postData.rating = null
            }

            PostType.FREE.label -> {
                postData.rating = null
                postData.isHidden = false
            }

            PostType.REVIEW.label -> {
                postData.isHidden = false
            }
        }
        if (uri != null) {
            //보낼 이미지 있는 경우의 통신
            val file = UriUtil.toFile(context, uri!!)
            val pic = MultipartUtil.getImageBody(file)

            viewModelScope.launch {
                val requestBody =
                    gson.toJson(postData).toRequestBody("application/json".toMediaTypeOrNull())
                val response = repository.writePost(requestBody, pic)
                val type = "게시글 작성에"
                when (response) {
                    is NetworkResponse.Success -> {
                        _postWriteStateType.postValue(PostWriteStateType.SUCCESS)
                    }

                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type, _msgPostWrite)
                        _postWriteStateType.postValue(PostWriteStateType.FAIL)
                    }

                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type, _msgPostWrite)
                        _postWriteStateType.postValue(PostWriteStateType.FAIL)
                    }

                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type, _msgPostWrite)
                        _postWriteStateType.postValue(PostWriteStateType.FAIL)
                    }
                }
            }
        } else {
            //없는 경우의 통신
            viewModelScope.launch {
                val requestBody =
                    gson.toJson(postData).toRequestBody("application/json".toMediaTypeOrNull())
                val response = repository.writePost(requestBody, null)
                val type = "게시글 작성에"
                when (response) {
                    is NetworkResponse.Success -> {
                        _postWriteStateType.postValue(PostWriteStateType.SUCCESS)
                    }

                    is NetworkResponse.ApiError -> {
                        _postWriteStateType.postValue(PostWriteStateType.FAIL)
                    }

                    is NetworkResponse.NetworkError -> {
                        _postWriteStateType.postValue(PostWriteStateType.FAIL)
                    }

                    is NetworkResponse.UnknownError -> {
                        _postWriteStateType.postValue(PostWriteStateType.FAIL)
                    }
                }
            }
        }
    }

    fun initWriteState() {
        _postWriteStateType.postValue(PostWriteStateType.LOADING)
    }

    fun postDelete() {
        Log.d(TAG, "postDelete: 삭제 ${selectedPost.value}")
    }

    fun postModify() {
        Log.d(TAG, "postModify: 수정 ${selectedPost.value}")
    }

    fun writeReComment() {

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