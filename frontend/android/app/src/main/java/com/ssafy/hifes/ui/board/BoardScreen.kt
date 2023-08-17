package com.ssafy.hifes.ui.board

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.common.ChipsSelectable
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.ui.theme.PrimaryPink

private const val TAG = "BoardScreen"

@Composable
fun BoardScreen(
    navController: NavController,
    viewModel: BoardViewModel,
    mainViewModel: MainViewModel
) {
    val postList = viewModel.postList.observeAsState()
    val boardType = viewModel.boardType.observeAsState()
    val selectedFestival = mainViewModel.selectedFestival
    val userId = AppPreferences.getUserId()

    BackHandler(true) {
        Log.d(TAG, "BoardScreen: ")
        mainViewModel.getFestivalInfo(selectedFestival)
        navController.popBackStack()
    }

    LaunchedEffect(boardType.value) {
        when (boardType.value) {
            PostType.NOTIFICATION -> viewModel.getNotificationPostList(selectedFestival)
            PostType.ASK -> viewModel.getAskPostList(selectedFestival)
            PostType.FREE -> viewModel.getFreePostList(selectedFestival)
            PostType.REVIEW -> viewModel.getReviewPostList(selectedFestival)
            else -> {
                viewModel.initBoardType(PostType.NOTIFICATION)
                viewModel.getNotificationPostList(selectedFestival)
            }
        }
    }

    if (userId != null && boardType.value != null && postList.value != null) {

        Scaffold(
            topBar = {
                TopWithBack(
                    navController,
                    title = stringResource(id = R.string.board_appbar_title)
                )
            },
            floatingActionButton = {
                if (boardType.value != PostType.NOTIFICATION) {
                    FloatingActionButton(
                        containerColor = PrimaryPink,
                        contentColor = Color.White,
                        onClick = { navController.navigate(HifesDestinations.POST_WRITE_ROUTE) }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                }

            }
        ) {
            if (postList.value != null) {
                Log.d(TAG, "BoardScreen: 길이 ${postList.value!!}")
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
                        ),
                        when (boardType.value) {
                            PostType.NOTIFICATION -> 0
                            PostType.ASK -> 1
                            PostType.FREE -> 2
                            PostType.REVIEW -> 3
                            else -> 0
                        }
                    ) { index ->
                        when (index) {
                            0 -> viewModel.initBoardType(PostType.NOTIFICATION)
                            1 -> viewModel.initBoardType(PostType.ASK)
                            2 -> viewModel.initBoardType(PostType.FREE)
                            3 -> viewModel.initBoardType(PostType.REVIEW)
                        }
                    }
                    Divider(color = LightGrey, thickness = 2.dp)

                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(postList.value!!.size) { index ->
                            PostItem(
                                postData = postList.value!![index],
                                userId.toInt()
                            ) { postData ->
                                if (postData.createdBy == userId.toInt() || postData.isHidden == null || postData.isHidden == false) {
                                    viewModel.getPostDetail(postData)
                                    viewModel.initSelectedPost(postData)
                                    navController.navigate(HifesDestinations.BOARD_DETAIL_ROUTE)
                                }
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
    val mainViewModel: MainViewModel = viewModel()

    BoardScreen(
        navController = rememberNavController(),
        viewModel = homeViewModel,
        mainViewModel = mainViewModel
    )
}