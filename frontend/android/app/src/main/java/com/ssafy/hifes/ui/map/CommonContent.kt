package com.ssafy.hifes.ui.map

import androidx.compose.foundation.Image
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


@Composable
fun MapScreenContent(festival: OrganizedFestivalDto) {
    MapCard(festival)
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun MapPreview() {
//    MapCard(MutableList<OrganizedFestivalDto>())
}

@Composable
fun MapCard(festival: OrganizedFestivalDto) {
    Card(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        // score 나중에 서버에서 가져옴
        MapCommonContent(
            festival, 4.0
        )
    }
}

@Composable
fun DialogContent(festival: OrganizedFestivalDto, score: Double) {
    Spacer(modifier = Modifier.size(8.dp))
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
        MapCommonContent(festival = festival, score = score)
    }


}

@Composable
fun MapCommonContent(festival: OrganizedFestivalDto, score: Double) {
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
            StarScore(score)
            TextTitleWithContent(
                address = festival.fesAddress, startDate = CommonUtils.formatSqlDateToString(
                    festival.fesStartDate
                ), endDate = CommonUtils.formatSqlDateToString(festival.fesEndDate)
            )
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
fun TextTitleWithContent(address: String, startDate: String, endDate: String) {
    Spacer(modifier = Modifier.size(2.dp))
    Text(
        text = "장소",
        color = Color.Black,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
    Text(
        text = address,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.size(4.dp))
    Text(
        text = "일정",
        color = Color.Black,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
    Text(
        text = address,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
}