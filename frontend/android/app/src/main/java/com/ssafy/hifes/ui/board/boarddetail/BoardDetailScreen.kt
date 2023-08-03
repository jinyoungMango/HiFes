package com.ssafy.hifes.ui.board.boarddetail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.data.model.CommentDto
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.board.boarddetail.comment.CommentWriteComponent
import java.text.SimpleDateFormat

@Composable
fun BoardDetailScreen(navController: NavController, viewModel: BoardViewModel) {
    val selectedPost = viewModel.selectedPost.observeAsState()
    val localDensity = LocalDensity.current
    val scrollState = rememberScrollState()
    var height by remember { mutableStateOf(0.dp) }

    val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm")
    val testDate = java.sql.Date(formatter.parse("2023.04.25 12:03").time)

    val commentList: MutableList<CommentDto> = mutableListOf()
    commentList.apply {
        add(
            CommentDto(
                commentId = 1,
                normalUserId = 1,
                postId = 1,
                festivalId = 1,
                hostId = 1,
                comment = "댓글내용",
                createdAt = testDate,
                createdBy = "사용자 닉네임",
                updatedAt = testDate,
                depth = false
            )
        )
        add(
            CommentDto(
                commentId = 1,
                normalUserId = 2,
                postId = 1,
                festivalId = 1,
                hostId = 1,
                comment = "댓글내용",
                createdAt = testDate,
                createdBy = "사용자 닉네임",
                updatedAt = testDate,
                depth = false
            )
        )
    }
    val reCommentList: MutableList<CommentDto> = mutableListOf()
    reCommentList.apply {
        add(
            CommentDto(
                commentId = 1,
                normalUserId = 1,
                postId = 1,
                festivalId = 1,
                hostId = 1,
                comment = "댓글내용",
                createdAt = testDate,
                createdBy = "사용자 닉네임",
                updatedAt = testDate,
                depth = true
            )
        )
        add(
            CommentDto(
                commentId = 1,
                normalUserId = 1,
                postId = 1,
                festivalId = 1,
                hostId = 1,
                comment = "댓글내용",
                createdAt = testDate,
                createdBy = "사용자 닉네임",
                updatedAt = testDate,
                depth = true
            )
        )
    }

    if (selectedPost.value != null) {
        Scaffold(
            topBar = { BoardDetailTopAppBar(selectedPost.value!!, viewModel) },
            content = { it ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(it)
                ) {
                    LazyColumn(modifier = Modifier.padding(16.dp, 0.dp)) {
                        item {
                            Column() {
                                BoardDetailHead(postData = selectedPost.value!!)
                                BoardDetailBody(postData = selectedPost.value!!)
                                Spacer(modifier = Modifier.size(20.dp))
                                Divider()
                                Spacer(modifier = Modifier.size(20.dp))
                            }
                        }
                        items(commentList.size) { index ->
                            BoardDetailComments(
                                viewModel = viewModel,
                                commentList = commentList,
                                reCommentList = reCommentList
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.size(height))
                        }

                    }
                    when(selectedPost.value!!.postType){
                        PostType.ASK.label ->{
                            if(selectedPost.value!!.normalUserId==viewModel.userDataId){
                                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                                    CommentWriteComponent(
                                        viewModel = viewModel,
                                        onGloballyPositioned = { layoutCoordinates ->
                                            height = with(localDensity) {
                                                layoutCoordinates.size.height.toDp()
                                            }
                                        }
                                    )
                                }
                            }
                        }
                        PostType.REVIEW.label->{
                            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                                CommentWriteComponent(
                                    viewModel = viewModel,
                                    onGloballyPositioned = { layoutCoordinates ->
                                        height = with(localDensity) {
                                            layoutCoordinates.size.height.toDp()
                                        }
                                    }
                                )
                            }
                        }
                        PostType.FREE.label->{
                            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                                CommentWriteComponent(
                                    viewModel = viewModel,
                                    onGloballyPositioned = { layoutCoordinates ->
                                        height = with(localDensity) {
                                            layoutCoordinates.size.height.toDp()
                                        }
                                    }
                                )
                            }
                        }
                    }

                }
            }
        )
    }
}

@Composable
@Preview
fun PreviewBoardDetailScreen() {
    val festivalTableList = mutableListOf<FestivalTableDto>()
    val formatter = SimpleDateFormat("yyyy.MM.dd")
    val testDate = java.sql.Date(formatter.parse("2023.04.25").time)

    var viewModel = BoardViewModel()
    viewModel.getPostDetail(
        PostDto(
            1,
            1,
            1,
            1,
            "제목",
            "내용",
            "review",
            null,
            null,
            "글쓴이",
            testDate,
            testDate,
            1,
            null,
            5f
        )
    )
    BoardDetailScreen(navController = rememberNavController(), viewModel = BoardViewModel())
}

