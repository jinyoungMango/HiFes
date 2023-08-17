package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ssafy.hifes.ui.common.HashtagChips
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfoundsmall
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun GroupDetailScreen(
    navController: NavController,
    viewModel: GroupViewModel
) {

    var selectedGroupId = viewModel.selectedGroup.observeAsState()
    var groupDetailInfo = viewModel.groupDetailInfo.observeAsState()
    var groupImages = viewModel.groupImages.observeAsState()

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        if (selectedGroupId.value != null && groupDetailInfo.value != null) {
            GroupDialog(
                navController,
                groupViewModel = viewModel,
                groupId = selectedGroupId.value!!,
                groupDetailInfo.value!!.isJoinedGroup
            ) {
                showDialog = false
            }
        }
    }

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
                        .height(320.dp).background(color = Color.White)
                ) {
                    AsyncImage(
                        model = groupDetailInfo.value!!.groupPic,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth(),
                        error = rememberVectorPainter(MyIconPack.Imagenotfoundsmall)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Box() {
                    Column(modifier = Modifier.padding(18.dp)) {
                        GroupTitle(
                            title = groupDetailInfo.value!!.groupName,
                            num = groupDetailInfo.value!!.numOfJoinedPeople
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = groupDetailInfo.value!!.groupContent,
                            fontFamily = pretendardFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                        if (groupDetailInfo.value!!.hashtags != null) {
                            Spacer(modifier = Modifier.height(18.dp))
                            HashtagChips(
                                chips = groupDetailInfo.value!!.hashtags!!,
                                isDeleteable = false,
                                onDeleteButtonClicked = {}
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
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
                        groupDetailInfo.value!!.isJoinedGroup
                    ) {
                        showDialog = true
                    }
                }
            }
        }
    }


}