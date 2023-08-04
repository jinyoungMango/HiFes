package com.ssafy.hifes.ui.group.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.PrimaryPink

data class Group(
    val url: String,
    val title: String,
    val content: String,
    val hashtag: List<String>,
    val currNum: Int,
    val maxNum: Int
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupMainScreen(navController: NavController) {
    val tmp = Group(
        "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
        "제목",
        "내용",
        hashtag = listOf("1", "2", "3", "4"),
        3,
        6
    )
    var groupList = listOf(tmp, tmp, tmp,tmp, tmp, tmp,tmp, tmp, tmp,tmp, tmp, tmp)

    Scaffold(
        topBar = { TopWithBack(navController, title = "모임") },
        content = {
            Column (
                Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp)) {
                SearchGroup()
                Spacer(Modifier.height(16.dp))
                LazyColumn(Modifier.weight(1f)) {
                    items(groupList) { item ->
                        GroupItem(
                            url = item.url,
                            title = item.title,
                            content = item.content,
                            hashtag = item.hashtag,
                            currNum = item.currNum,
                            maxNum = item.maxNum
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  navController.navigate(HifesDestinations.GROUP_CREATE) },
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
    GroupMainScreen(navController = rememberNavController())
}