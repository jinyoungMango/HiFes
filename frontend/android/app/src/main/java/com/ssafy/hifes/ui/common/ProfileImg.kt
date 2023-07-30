package com.ssafy.hifes.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Camera

@Composable
fun ProfileImg() {
    Box() {
        AsyncImage(
            model = "https://mblogthumb-phinf.pstatic.net/MjAxODAzMDNfMTc5/MDAxNTIwMDQxNzQwODYx.qQDg_PbRHclce0n3s-2DRePFQggeU6_0bEnxV8OY1yQg.4EZpKfKEOyW_PXOVvy7wloTrIUzb71HP8N2y-YFsBJcg.PNG.osy2201/1_%2835%ED%8D%BC%EC%84%BC%ED%8A%B8_%ED%9A%8C%EC%83%89%29_%ED%9A%8C%EC%83%89_%EB%8B%A8%EC%83%89_%EB%B0%B0%EA%B2%BD%ED%99%94%EB%A9%B4_180303.png?type=w800",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = ColorPainter(Color.Gray),
            modifier = Modifier
                .size(160.dp)
                .padding(8.dp)
                .clip(CircleShape)
        )
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomEnd).padding(end = 24.dp)) {
            Image(
                imageVector = MyIconPack.Camera, // SVG 파일을 지정
                contentDescription = "Search",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProfilePrev() {
    ProfileImg()
}