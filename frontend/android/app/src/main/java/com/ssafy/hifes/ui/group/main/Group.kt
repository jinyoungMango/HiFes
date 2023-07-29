package com.ssafy.hifes.ui.group.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun Group(
    url: String,
    title: String,
    content: String,
    hashtag: List<String>,
    currNum: Int,
    maxNum: Int
) {
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 16.dp), modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp)) {
                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = ColorPainter(Color.Green),
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .align(Bottom)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(Modifier.fillMaxWidth()) {
                        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = content)
                        Spacer(modifier = Modifier.height(4.dp))
                        LazyRow() {
                            items(hashtag) { item ->
                                Text(text = "#$item")
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }
                    }
                }
                Text(text = "$currNum / $maxNum", modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 8.dp), textAlign = TextAlign.End)
            }
        }
    }




@Preview
@Composable
fun GroupPrev() {
    val hashtag = listOf("1", "2", "3", "4")

    Column(Modifier.padding(horizontal = 8.dp)) {
        Group(
            "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
            "제목",
            "내용",
            hashtag = hashtag,
            3,
            6
        )
        Spacer(modifier = Modifier.height(24.dp))

        Group(
            "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
            "제목",
            "내용",
            hashtag = hashtag,
            3,
            6
        )
        Spacer(modifier = Modifier.height(24.dp))

        Group(
            "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
            "제목",
            "내용",
            hashtag = hashtag,
            3,
            6
        )
        Spacer(modifier = Modifier.height(24.dp))

        Group(
            "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
            "제목",
            "내용",
            hashtag = hashtag,
            3,
            6
        )
    }
}