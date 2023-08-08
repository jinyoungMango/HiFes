package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun GroupTitle(title: String, num: Int) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontFamily = pretendardFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "인원",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "$num 명",
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun GroupTitlePrev() {
    GroupTitle("대구 치맥 파티 모임명", 6)
}