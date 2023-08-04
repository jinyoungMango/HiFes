package com.ssafy.hifes.ui.mypage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubTitleText(title: String, onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString(title), onClick = { onClick() },
        style = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 16.sp
        ),
        modifier = Modifier
            .padding(0.dp, 4.dp)
    )
}


@Composable
@Preview
fun SubTitleTextPrev() {
    SubTitleText(title = "테스트", onClick = {})
}