package com.ssafy.hifes.ui.group.info.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ssafy.hifes.ui.theme.pretendardFamily


@Composable
fun ChatBubble(
    chatItem: ChatItem,
    modifier: Modifier = Modifier
) {
    val bubbleColor = if (chatItem.isSentByUser) {
        Color(0xFFFFE5AD)
    } else {
        Color(0xFFE8E8E8)
    }

    val bubbleShape = if (chatItem.isSentByUser) {
        RoundedCornerShape(16.dp, 0.dp, 16.dp, 16.dp)
    } else {
        RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp)
    }

    val bubbleArrange = if (chatItem.isSentByUser) {
        Alignment.End
    } else {
        Alignment.Start
    }

    val textColor = Color.Black
    val padding = 8.dp
    Row() {
        if (!chatItem.isSentByUser) {
            AsyncImage(
                model = "https://mblogthumb-phinf.pstatic.net/MjAxODAzMDNfMTc5/MDAxNTIwMDQxNzQwODYx.qQDg_PbRHclce0n3s-2DRePFQggeU6_0bEnxV8OY1yQg.4EZpKfKEOyW_PXOVvy7wloTrIUzb71HP8N2y-YFsBJcg.PNG.osy2201/1_%2835%ED%8D%BC%EC%84%BC%ED%8A%B8_%ED%9A%8C%EC%83%89%29_%ED%9A%8C%EC%83%89_%EB%8B%A8%EC%83%89_%EB%B0%B0%EA%B2%BD%ED%99%94%EB%A9%B4_180303.png?type=w800",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.Gray),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = bubbleArrange) {
            if (!chatItem.isSentByUser) {
                Text(text = "이름")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
            ) {
                Row(
                    modifier = Modifier
                        .background(color = bubbleColor, shape = bubbleShape,)
                        .padding(padding)
                ) {
                    Text(
                        text = chatItem.text,
                        color = textColor,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "8:29 pm", color = Color.Gray, fontFamily = pretendardFamily, fontWeight = FontWeight.Light)

        }
    }

}

@Preview
@Composable
fun ChatBubblePrev() {
    Column(
    ) {
        ChatBubble(ChatItem(text = "Hello!", isSentByUser = true), modifier = Modifier.padding(bottom = 8.dp))
        ChatBubble(ChatItem(text = "Hello!", isSentByUser = false), modifier = Modifier.padding(bottom = 8.dp))
        ChatBubble(ChatItem(text = "Hello!", isSentByUser = false), modifier = Modifier.padding(bottom = 8.dp))
    }
}