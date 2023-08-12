package com.ssafy.hifes.ui.group.info

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.group.info.chat.GroupChatScreen
import com.ssafy.hifes.ui.group.info.detail.GroupDetailScreen
import com.ssafy.hifes.ui.group.info.picture.GroupPictureScreen
import com.ssafy.hifes.ui.theme.PrimaryPink

@Composable
fun GroupInfoScreen(
    navController: NavController,
    viewModel: GroupViewModel
) {
    var selectedTab by remember { mutableStateOf(0) }
    var title = if (selectedTab == 0) "모임 상세"
    else if (selectedTab == 1) "모임 사진"
    else "모임 채팅"
    var floatingButton: @Composable (() -> Unit)? = null

    if (selectedTab == 1) {
        floatingButton = {
            FloatingActionButton(
                onClick = { /* Handle FAB click */ },
                containerColor = PrimaryPink,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }

    Scaffold(topBar = { TopWithBack(navController, title = title) }, floatingActionButton = {
        floatingButton?.invoke()
    }) { it ->
        Column(modifier = Modifier.padding(it)) {
            GroupTab(
                modifier = Modifier.padding(it),
                selected = selectedTab,
                setSelected = { selectedTab = it },
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (selectedTab == 0) {
                GroupDetailScreen(navController, viewModel)
            } else if (selectedTab == 1) {
                GroupPictureScreen()
            } else if (selectedTab == 2) {
                GroupChatScreen()
            }
        }
    }
}

@Preview
@Composable
fun GroupInfoScreenPrev() {
    // GroupInfoScreen(rememberNavController(), GroupViewModel())
}