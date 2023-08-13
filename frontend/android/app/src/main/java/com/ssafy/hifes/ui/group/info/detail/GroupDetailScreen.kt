package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ssafy.hifes.ui.common.HashtagChips
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfoundsmall

@Composable
fun GroupDetailScreen(
    navController: NavController,
    viewModel: GroupViewModel
) {

    var selectedGroupId = viewModel.selectedGroup.observeAsState()
    var groupDetailInfo = viewModel.groupDetailInfo.observeAsState()
    var groupImages = viewModel.groupImages.observeAsState()

    LaunchedEffect(selectedGroupId) {
        if (selectedGroupId.value != null) {
            viewModel.getGroupDetail(selectedGroupId.value!!)
            viewModel.getGroupImages(selectedGroupId.value!!)
        }
    }

    if (groupDetailInfo.value != null) {
        LazyColumn {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                ) {
                    AsyncImage(
                        model = groupDetailInfo.value!!.groupPic,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = ColorPainter(Color.Green),
                        modifier = Modifier
                            .fillMaxWidth(),
                        error = rememberVectorPainter(MyIconPack.Imagenotfoundsmall)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Box() {
                    Column(modifier = Modifier.padding(12.dp)) {
                        GroupTitle(
                            title = groupDetailInfo.value!!.groupName,
                            num = groupDetailInfo.value!!.numOfJoinedPeople
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        if (groupDetailInfo.value!!.hashtags != null) {
                            HashtagChips(chips = groupDetailInfo.value!!.hashtags!!)
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        if (groupImages.value != null && groupImages.value!!.size > 0) {
                            GroupPictureRow(groupImages.value!!)
                        }
                        Spacer(modifier = Modifier.height(24.dp))

                        if (groupDetailInfo.value!!.joinedPeople.size > 0) {
                            GroupMemberRow(groupMember = groupDetailInfo.value!!.joinedPeople)
                        }
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Leave(
                        groupDetailInfo.value!!.isJoinedGroup,
                        { it ->

                        }
                    )
                }
            }
        }
    }


}

@Preview
@Composable
fun GroupDetailScreenPrev() {
    //GroupDetailScreen(rememberNavController(), GroupViewModel())
}