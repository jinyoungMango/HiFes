package com.ssafy.hifes.ui.board.boarddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.BoardViewModel

@Composable
fun BoardDetailScreen(navController: NavController, viewModel: BoardViewModel) {
    val selectedPost = viewModel.selectedPost.observeAsState()
    Column() {
        BoardDetailTopAppBar(viewModel.selectedPostType)
        Column(modifier = Modifier.padding(16.dp, 0.dp)) {
            if (selectedPost.value != null) {
                BoardDetailHead(postData = selectedPost.value!!)
                BoardDetailBody(postData = selectedPost.value!!)
            }
        }
    }
}

