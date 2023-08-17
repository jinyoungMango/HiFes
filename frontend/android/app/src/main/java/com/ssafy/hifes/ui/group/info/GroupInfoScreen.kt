package com.ssafy.hifes.ui.group.info

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.group.create.GroupCreateStateType
import com.ssafy.hifes.ui.group.info.chat.ChatViewModel
import com.ssafy.hifes.ui.group.info.chat.GroupChatScreen
import com.ssafy.hifes.ui.group.info.detail.GroupDetailScreen
import com.ssafy.hifes.ui.group.info.detail.GroupPictureGrid
import com.ssafy.hifes.ui.mypage.MyPageViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink

private const val TAG = "GroupInfoScreen_하이페스"

@Composable
fun GroupInfoScreen(
    navController: NavController,
    groupViewModel: GroupViewModel,
    chatViewModel: ChatViewModel
) {
    var context = LocalContext.current
    var selectedTab by remember { mutableStateOf(0) }
    var groupDetailInfo = groupViewModel.groupDetailInfo.observeAsState()
    val imageErrMsg = groupViewModel.errorMsgGroupImages.observeAsState()
    val detailErrMsg = groupViewModel.errorMsgGroupDetail.observeAsState()
    val groupImages = groupViewModel.groupImages.observeAsState()
    val uploadState by groupViewModel.uploadPictureStateType.observeAsState()

    imageErrMsg.value?.getContentIfNotHandled()?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
    detailErrMsg.value?.getContentIfNotHandled()?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
    LaunchedEffect(uploadState) {
        groupViewModel.selectedGroup.value?.let { groupViewModel.getGroupImages(it) }
        groupViewModel.initUploadPictureState()
    }

    var title = if (selectedTab == 0) stringResource(id = R.string.group_top_detail)
    else if (selectedTab == 1) stringResource(id = R.string.group_top_photo)
    else stringResource(id = R.string.group_top_chat)
    var floatingButton: @Composable (() -> Unit)? = null

    val getContent =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                groupViewModel.selectedGroup.value?.let { groupId ->
                    groupViewModel.uploadPicture(
                        context, uri,
                        groupId
                    )

                    Toast.makeText(context, "이미지가 업로드되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }

    if (selectedTab == 1) {
        floatingButton = {
            FloatingActionButton(
                onClick = { getContent.launch("image/*") },
                containerColor = PrimaryPink,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }

    Scaffold(topBar = {
        TopWithBack(navController, title = title)
    }, floatingActionButton = {
        floatingButton?.invoke()
    }) { it ->
        Column(modifier = Modifier.padding(it).background(color = Color.White)) {
            // 가입 여부에 따라 보여주기
            if (groupDetailInfo.value?.isJoinedGroup == true) {
                GroupTab(
                    modifier = Modifier.padding(it),
                    selected = selectedTab,
                    setSelected = { selectedTab = it },
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            if (selectedTab == 0) {
                GroupDetailScreen(navController, groupViewModel)
            } else if (selectedTab == 1) {
                if (!groupImages.value.isNullOrEmpty()) {
                    GroupPictureGrid(groupViewModel)
                }
            } else if (selectedTab == 2) {
                GroupChatScreen(chatViewModel, groupViewModel)
            }
        }
    }

}
