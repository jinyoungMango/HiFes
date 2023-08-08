package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ssafy.hifes.ui.common.HashtagChips
import com.ssafy.hifes.ui.group.GroupViewModel

@Composable
fun GroupDetailScreen(navController: NavController, viewModel: GroupViewModel) {
    val user = User(
        url = "https://picsum.photos/600",
        name = "name"
    )
    val img = Img(
        url = "https://picsum.photos/600"
    )

    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                AsyncImage(
                    model = img.url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(Color.Green),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Box() {
                Column(modifier = Modifier.padding(12.dp)) {
                    GroupTitle(title = "치맥 파티 가자", num = 6)
                    Spacer(modifier = Modifier.height(24.dp))
                    HashtagChips(chips = listOf("#6명", "대구치맥파티", "#치킨", "맥주", "20대"))
                    Spacer(modifier = Modifier.height(24.dp))
                    GroupPictureRow(img = listOf(img, img, img, img, img, img, img))
                    Spacer(modifier = Modifier.height(24.dp))
                    GroupMemberRow(groupMember = listOf(user, user, user, user, user, user))
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                Leave()
            }
        }
    }
}

@Preview
@Composable
fun GroupDetailScreenPrev() {
    GroupDetailScreen(rememberNavController(), GroupViewModel())
}