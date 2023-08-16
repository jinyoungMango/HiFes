package com.ssafy.hifes.ui.board.boarddetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.board.boarddetail.comment.CommentWriteComponent

@Composable
fun BoardDetailScreen(navController: NavController, viewModel: BoardViewModel) {
    var userId = AppPreferences.getUserId()
    val selectedPost = viewModel.selectedPost.observeAsState()
    val localDensity = LocalDensity.current
    val scrollState = rememberScrollState()
    var height by remember { mutableStateOf(0.dp) }

    if (selectedPost.value != null && userId != null) {
        Scaffold(
            topBar = { BoardDetailTopAppBar(navController, selectedPost.value!!, viewModel) },
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
                                BoardDetailHead(
                                    postData = selectedPost.value!!,
                                    userDataId = userId
                                )
                                BoardDetailBody(postData = selectedPost.value!!)
                                Spacer(modifier = Modifier.size(20.dp))
                                Divider()
                                Spacer(modifier = Modifier.size(20.dp))
                            }
                        }

                        BoardDetailComments(
                            viewModel = viewModel,
                            commentList = selectedPost.value!!.topLevelComments
                        )

                        item {
                            Spacer(modifier = Modifier.size(50.dp))
                        }

                    }
                    when (selectedPost.value!!.postType) {
                        PostType.ASK.label -> {
                            if (selectedPost.value!!.createdBy.toString() == userId) {
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

                        PostType.REVIEW.label -> {
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

                        PostType.FREE.label -> {
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
//    val festivalTableList = mutableListOf<FestivalTableDto>()
//    val formatter = SimpleDateFormat("yyyy.MM.dd")
//    val testDate = java.sql.Date(formatter.parse("2023.04.25").time)
//
//    var viewModel = BoardViewModel()
//    viewModel.getPostDetail(
//        PostDto(
//            1,
//            1,
//            1,
//            1,
//            "제목",
//            "내용",
//            "review",
//            null,
//            null,
//            "글쓴이",
//            testDate,
//            testDate,
//            1,
//            null,
//            5f
//        )
//    )
//    BoardDetailScreen(navController = rememberNavController(), viewModel = BoardViewModel())
}

