package com.ssafy.hifes.ui.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.boardcommon.PostType
import java.text.SimpleDateFormat

class BoardViewModel : ViewModel() {
    val userDataId : Int = 1
    private var _postList : MutableLiveData<MutableList<PostDto>> = MutableLiveData()
    val postList : LiveData<MutableList<PostDto>> = _postList

    private var _selectedPost : MutableLiveData<PostDto> = MutableLiveData()
    val selectedPost : LiveData<PostDto> = _selectedPost

    var selectedPostType = "notification"

    lateinit var postTestDate : java.sql.Date

    init {
        val formatter = SimpleDateFormat("yyyy.MM.dd")
        postTestDate = java.sql.Date(formatter.parse("2023.04.25").time)
        getNotificationPostList()
    }

    fun getNotificationPostList(){//추후 서버 통신 코드가 생기면 이 부분을 서버에게서 공지 게시글 리스트를 받아오는것으로 변경한다
        var postListDummyData = mutableListOf<PostDto>()
        postListDummyData.apply {
            add(PostDto(1, 1, 1, 1, "공지1", "내용", "notification", null, null, "글쓴이", postTestDate, postTestDate, 1, null, null))
            add(PostDto(1, 1, 1, 1, "공지2", "내용", "notification", null, null, "글쓴이", postTestDate, postTestDate, 1, null, null))
            add(PostDto(1, 1, 1, 1, "공지3", "내용", "notification", null, null, "글쓴이", postTestDate, postTestDate, 1, null, null))
            add(PostDto(1, 1, 1, 2, "공지4", "내용", "notification", null, null, "글쓴이", postTestDate, postTestDate, 1, "https://picsum.photos/600", null))
            add(PostDto(1, 1, 1, 2, "공지5", "내용", "notification", null, null, "글쓴이", postTestDate, postTestDate, 1, null, null))
        }
        _postList.postValue(postListDummyData)
    }

    fun getAskPostList(){
        var postListDummyData = mutableListOf<PostDto>()
        postListDummyData.apply {
            add(PostDto(1, 1, 1, 1, "내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글내문의1 비밀글", "내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1내용1", "ask", true, null, "글쓴이", postTestDate, postTestDate, 1, "https://fastly.picsum.photos/id/296/200/200.jpg?hmac=y-H33xJ0Tpm9muoZO3ZMb5kXpNPG1mptQ9HBmpjCc8A", null))
            add(PostDto(1, 1, 1, 1, "내문의2 공개글", "내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2내용2", "ask", false, null, "글쓴이", postTestDate, postTestDate, 1, "https://picsum.photos/1000", null))
            add(PostDto(1, 1, 1, 2, "남의 문의1 비밀글", "내용3", "ask", true, null, "글쓴이", postTestDate, postTestDate, 1, "https://picsum.photos/600", null))
            add(PostDto(1, 1, 1, 2, "남의 문의2 공개글", "내용4", "ask", false, null, "글쓴이", postTestDate, postTestDate, 1, "https://picsum.photos/1000", null))
        }
        _postList.postValue(postListDummyData)
    }

    fun getFreePostList(){
        var postListDummyData = mutableListOf<PostDto>()
        postListDummyData.apply {
            add(PostDto(1, 1, 1, 1, "자유글1", "내용1", "free", null, null, "글쓴이", postTestDate, postTestDate, 1, null, null))
            add(PostDto(1, 1, 1, 1, "자유글2", "내용2", "free", null, null, "글쓴이", postTestDate, postTestDate, 1, "https://picsum.photos/1600", null))
            add(PostDto(1, 1, 1, 1, "자유글3", "내용3", "free", null, null, "글쓴이", postTestDate, postTestDate, 1, null, null))
            add(PostDto(1, 1, 1, 2, "자유글4", "내용4", "free", null, null, "글쓴이", postTestDate, postTestDate, 1, "https://picsum.photos/2000", null))
            add(PostDto(1, 1, 1, 2, "자유글5", "내용5", "free", null, null, "글쓴이", postTestDate, postTestDate, 1, null, null))
        }
        _postList.postValue(postListDummyData)
    }

    fun getReviewPostList(){
        var postListDummyData = mutableListOf<PostDto>()
        postListDummyData.apply {
            add(PostDto(1, 1, 1, 1, "리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1리뷰1", "내용내용내용내용내용내용내용내용내용내용내용", "review", null, null, "글쓴이", postTestDate, postTestDate, 1, null, 0f))
            add(PostDto(1, 1, 1, 1, "리뷰2", "내용", "review", null, null, "글쓴이", postTestDate, postTestDate, 1, "https://picsum.photos/1000", 3f))
            add(PostDto(1, 1, 1, 1, "리뷰3", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용", "review", null, null, "글쓴이", postTestDate, postTestDate, 1, "https://fastly.picsum.photos/id/296/200/200.jpg?hmac=y-H33xJ0Tpm9muoZO3ZMb5kXpNPG1mptQ9HBmpjCc8A", 5f))
            add(PostDto(1, 1, 1, 2, "리뷰4", "내용", "review", null, null, "글쓴이", postTestDate, postTestDate, 1, null, 3.5f))
            add(PostDto(1, 1, 1, 2, "리뷰5", "내용", "review", null, null, "글쓴이", postTestDate, postTestDate, 1, null, 2.5f))
        }
        _postList.postValue(postListDummyData)
    }

    fun getPostDetail(postData : PostDto){//추후 서버 통신 코드가 생기면 이 부분을 서버에게서 게시글 상세를 받아오는 부분으로 변경한다
        selectedPostType = postData.postType
        _selectedPost.postValue(postData)
    }


}