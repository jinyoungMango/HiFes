package com.ssafy.hifes.ui.group.main

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun GroupItem(
    group: Group,
    onClick: (Group) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(group)
            },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            ) {
                AsyncImage(
                    model = group.url,
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
                    Text(
                        text = group.title,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = group.content,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow() {
                        items(group.hashtag) { item ->
                            Text(
                                text = "#$item",
                                fontFamily = pretendardFamily,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                }
            }
            Text(
                text = "${group.currNum} / ${group.maxNum}", modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 8.dp), textAlign = TextAlign.End,
                fontFamily = pretendardFamily, fontWeight = FontWeight.Normal
            )
        }
    }
}