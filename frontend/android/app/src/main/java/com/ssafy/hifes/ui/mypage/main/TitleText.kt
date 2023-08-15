package com.ssafy.hifes.ui.mypage.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun TitleText(title: String) {
    Text(
        text = title,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}


@Composable
@Preview
fun TitleTextPrev() {
    TitleText(title = "테스트")
}