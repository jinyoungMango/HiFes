package com.ssafy.hifes.ui.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.R

@Composable
fun BoardScreen(
    navController: NavController,
    viewModel : BoardViewModel
) {
    val postList = viewModel.postList.observeAsState()

    Scaffold (
        topBar = {TopWithBack(title = stringResource(id = R.string.board_appbar_title))}
    ){
        if(postList.value!=null){
            Column(modifier = Modifier.padding(it)) {
                Button(onClick = {viewModel.getAskPostList()}) {
                    Text(text = "공지글 보기")
                }
                Button(onClick = {viewModel.getFreePostList()}) {
                    Text(text = "자유글 보기")
                }
                Button(onClick = {viewModel.getReviewPostList()}) {
                    Text(text = "리뷰글 보기")
                }
                LazyColumn{
                    items(postList.value!!.size){index ->
                        PostItem(postData = postList.value!!.get(index), viewModel.userDataId, {})
                    }
                }
            }

        }
    }


}

@Composable
@Preview
fun PreviewBoardScreen(){
    val homeViewModel: BoardViewModel = viewModel()

    BoardScreen(navController = rememberNavController(), viewModel = homeViewModel)
}