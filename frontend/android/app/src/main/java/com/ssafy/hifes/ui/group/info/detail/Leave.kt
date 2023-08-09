package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Leave
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun Leave() {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                imageVector = MyIconPack.Leave,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
        }
        Text(
            text = "탈퇴하기",
            color = Color(0xFF979797),
            modifier = Modifier.clickable { },
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun LeavePrev() {
    Leave()
}