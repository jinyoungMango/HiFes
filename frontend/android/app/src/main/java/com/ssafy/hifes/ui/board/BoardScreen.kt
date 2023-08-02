package com.ssafy.hifes.ui.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.common.ChipsSelectable
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.LightGrey

@Composable
fun BoardScreen(
    navController: NavController,
    viewModel: BoardViewModel
) {
    val postList = viewModel.postList.observeAsState()

    Scaffold(
        topBar = { TopWithBack(title = stringResource(id = R.string.board_appbar_title)) }
    ) {
        if (postList.value != null) {
            Column(
                modifier = Modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChipsSelectable(
                    listOf(
                        stringResource(id = R.string.board_chip_notification),
                        stringResource(id = R.string.board_chip_ask),
                        stringResource(id = R.string.board_chip_free),
                        stringResource(id = R.string.board_chip_review)
                    )
                ) { index ->
                    when (index) {
                        0 -> viewModel.getNotificationPostList()
                        1 -> viewModel.getAskPostList()
                        2 -> viewModel.getFreePostList()
                        3 -> viewModel.getReviewPostList()
                    }
                }
                Divider(color = LightGrey, thickness = 2.dp)

                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(postList.value!!.size) { index ->
                        PostItem(
                            postData = postList.value!!.get(index),
                            viewModel.userDataId
                        ) { postData ->
                            if(postData.normalUserId == viewModel.userDataId || postData.hidden==null || postData.hidden == false){
                                viewModel.getPostDetail(postData)
                                navController.navigate(HifesDestinations.BOARD_DETAIL_ROUTE)
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
@Preview
fun PreviewBoardScreen() {
    val homeViewModel: BoardViewModel = viewModel()

    BoardScreen(navController = rememberNavController(), viewModel = homeViewModel)
}