package com.ssafy.hifes.ui.map

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.ui.theme.pretendardFamily
import com.ssafy.hifes.util.CommonUtils

private const val TAG = "CommonContent_하이페스"
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun MapPreview() {
//    MapCard(MutableList<OrganizedFestivalDto>())
}

@Composable
fun MapCard(festival: OrganizedFestivalDto, onClick: (OrganizedFestivalDto) -> Unit) {
    Card(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onClick(festival)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        MapCommonContent(
            festival
        )
    }
}

@Composable
fun DialogContent(
    festival: OrganizedFestivalDto,
    onClick: (OrganizedFestivalDto) -> Unit
) {
    Log.d(TAG, "DialogContent: $festival")
    Spacer(modifier = Modifier.size(8.dp))
    Column(modifier = Modifier.clickable {
        onClick(festival)
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = festival.fesTitle,
                fontFamily = pretendardFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.size(8.dp))
        Divider(color = LightGrey, thickness = 2.dp)
        MapCommonContent(festival = festival, false)
    }


}

@Composable
fun MapCommonContent(festival: OrganizedFestivalDto, isViewPager: Boolean = true) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(12.dp)
    ) {
        AsyncImage(
            model = festival.fesPosterPath,
            contentDescription = "Poster Image",
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            if (isViewPager) {
                Text(
                    text = festival.fesTitle,
                    color = Color.Black,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.size(4.dp))
            }
            StarScore(festival.avgRating)
            TextTitleWithContent(
                title = "장소",
                content = festival.fesAddress
            )
            if (!isViewPager) {
                val date = "${
                    CommonUtils.formatFestivalDateToString(festival.fesStartDate)
                } ~ ${CommonUtils.formatFestivalDateToString(festival.fesEndDate)}"
                TextTitleWithContent(
                    title = "일정",
                    content = date
                )
            }
        }
    }
}


@Composable
fun StarScore(score: Double) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.icon_star),
            contentDescription = "star",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = score.toString(),
            color = Color.Black,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun TextTitleWithContent(title: String, content: String) {
    Spacer(modifier = Modifier.size(2.dp))
    Text(
        text = title,
        color = Color.Black,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
    Text(
        text = content,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )

}