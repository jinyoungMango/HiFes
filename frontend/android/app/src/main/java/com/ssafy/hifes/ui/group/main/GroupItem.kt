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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun GroupItem(
    group: Group,
    onClick: (Group) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick(group)
            },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = group.groupPic,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.Green),
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.size(18.dp))
                Text(
                    text = group.groupName,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = group.content,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (group.hashtags != null) {
                    LazyRow() {
                        items(group.hashtags) { item ->
                            Text(
                                text = "#$item",
                                fontFamily = pretendardFamily,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                }
                Text(
                    text = "${group.numOfPeople} / ${group.maxPop}", modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp, bottom = 8.dp), textAlign = TextAlign.End,
                    fontFamily = pretendardFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp
                )
            }

        }


    }

}

@Preview
@Composable
fun GroupItemPreview() {
//    GroupItem(group = GroupViewModel().groupList.value[0]!!, onClick = )
}