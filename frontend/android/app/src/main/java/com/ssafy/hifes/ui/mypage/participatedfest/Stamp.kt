package com.ssafy.hifes.ui.mypage.participatedfest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Stamp
import com.ssafy.hifes.ui.theme.Grey
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.ui.theme.PrimaryPink

@Composable
fun Stamp(state: Boolean) {
    val backgroundColor = if (state) Color.White else LightGrey
    val iconColor = if (state) PrimaryPink else Grey
    Box(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = backgroundColor)
            .aspectRatio(1f)
    ) {
        Icon(
            imageVector = MyIconPack.Stamp,
            contentDescription = "스탬프",
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.Center),
            tint = iconColor
        )
    }
}

@Composable
@Preview
fun PreviewStamp() {
    Column() {
        Stamp(state = false)
        Spacer(modifier = Modifier.size(10.dp))
        Stamp(state = true)
    }
}