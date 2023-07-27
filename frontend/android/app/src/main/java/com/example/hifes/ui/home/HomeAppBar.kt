package com.example.hifes.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hifes.R


@Preview
@Composable
fun HomePrev() {
    HomeAppBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar() {
    val image: Painter = painterResource(id = R.drawable.icon_search)
    val otherImage: Painter = painterResource(id = R.drawable.icon_mypage) // 다른 아이콘 이미지

    TopAppBar(
        title = { Text(text = "My App") },
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) { // Row 레이아웃 사용
                var text by remember { mutableStateOf("") }

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("이벤트를 검색해보세요.") },
                    leadingIcon = {
                        IconButton(onClick = { /* do something on click */ }) {
                            Icon(
                                painter = image,
                                contentDescription = "Trailing icon",
                                modifier = Modifier.fillMaxSize(0.7f)
                            )
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(30.dp))
                        .background(color = Color.White)
                        .weight(1f) // TextField를 Row의 남은 공간에 채우도록 함
                )

                // 추가적인 아이콘
                IconButton(onClick = { /* do something on click */ }) {
                    Icon(
                        painter = otherImage,
                        contentDescription = "Other icon",
                        modifier = Modifier.fillMaxSize(1f)
                    )
                }
            }
        }
    )
}



