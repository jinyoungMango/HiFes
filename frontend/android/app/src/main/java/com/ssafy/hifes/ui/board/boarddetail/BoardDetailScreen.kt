package com.ssafy.hifes.ui.board.boarddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.BoardViewModel
import java.text.SimpleDateFormat

@Composable
fun BoardDetailScreen(navController: NavController, viewModel: BoardViewModel) {
    val selectedPost = viewModel.selectedPost.observeAsState()
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        if (selectedPost.value != null) {
            BoardDetailTopAppBar(selectedPost.value!!, viewModel)
            Column(modifier = Modifier.padding(16.dp, 0.dp)) {
                BoardDetailHead(postData = selectedPost.value!!)
                BoardDetailBody(postData = selectedPost.value!!)

            }
        }

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

