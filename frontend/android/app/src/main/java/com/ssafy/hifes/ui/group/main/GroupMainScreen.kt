package com.ssafy.hifes.ui.group.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink

@Composable
fun GroupMainScreen(navController: NavController, viewModel: GroupViewModel) {
    val groupList = viewModel.groupList.observeAsState()

    Scaffold(
        topBar = { TopWithBack(navController, title = "모임") },
        content = {
            Column(
                Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp)
            ) {
                SearchGroup()
                if (!groupList.value.isNullOrEmpty()) {
                    LazyColumn(Modifier.weight(1f)) {
                        items(groupList.value!!.size) { index ->
                            GroupItem(
                                groupList.value!![index]
                            ){ groupData ->
                                viewModel.getGroupDetail(groupData)
                                navController.navigate(HifesDestinations.GROUP_DETAIL)
                            }
                            Spacer(modifier = Modifier.size(4.dp))
                            if (index < groupList.value!!.size - 1) {
                                Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
                            }
                        }
                    }
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(HifesDestinations.GROUP_CREATE) },
                containerColor = PrimaryPink,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    )
}

@Preview
@Composable
fun GroupMainPrev() {
    GroupMainScreen(navController = rememberNavController(), GroupViewModel())
}