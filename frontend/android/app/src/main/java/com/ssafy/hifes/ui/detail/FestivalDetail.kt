package com.ssafy.hifes.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.map.StarScore
import com.ssafy.hifes.util.CommonUtils.formatDateToString
import java.util.Date


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun FestivalDetail(navController: NavHostController) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(4.dp)
    ) {

        Box {
            Image(image = painterResource(R.drawable.ic_launcher_foreground))
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                DetailIcons(painterResource(R.drawable.icon_board))
                DetailIcons(painterResource(R.drawable.icon_map))
            }
        }
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-8).dp),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                shadowElevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                    ) {
                        DetailIcons(painterResource(R.drawable.icon_share))
                    }
                    DetailTitle("2019 대구 치맥 페스티벌")
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StarScore(score = 4.0)
                        Spacer(modifier = Modifier.size(12.dp))
                        navigateToMeetingScreen("12개")
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    DetailContent("하늘이 내린 최고의 조합, 치킨과 맥주! 매년 여름, 대구에서 치킨과 맥주의 기막힌 조합을 테마로 한 대구치맥페스티벌이 열린다. 치맥페스티벌이라는 말 그대로 축제 기간 동안 맛있는 치킨과 시원한 맥주를 마음껏 즐기며 가수들의 공연을 관람할 수 있다.")

                }
            }
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                DetailCommonContent(
                    title = "일정",
                    content1 = formatDateToString(Date()),
                    content2 = formatDateToString(
                        Date()
                    )
                )
                Spacer(modifier = Modifier.size(12.dp))
                DetailCommonContent(title = "장소", address = "주소")
                Spacer(modifier = Modifier.size(12.dp))
                NaverMap(modifier = Modifier.size(300.dp))
                Spacer(modifier = Modifier.size(12.dp))
                DetailCommonContent(title = "주최", content1 = "대구광역시", content2 = "053 - 248 - 9998")
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun FestivalDetailPrev() {
    FestivalDetail(navController = rememberNavController())
}


@Composable
fun DetailCommonContent(title: String, content1: String, content2: String) {
    Column {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        when (title) {
            "일정" -> {
                Row {
                    Text(text = "$content1 ~ $content2", fontSize = 16.sp)
                }
            }

            "주최" -> {
                Text(text = content1, fontSize = 16.sp)
                Text(text = content2, fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun DetailCommonContent(title: String, address: String) {
    Column {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Row {
            Text(text = address, fontSize = 16.sp)
        }
    }
}

@Composable
fun DetailContent(
    content: String,
    maxLines: Int = 3, // 내용을 줄여서 보여줄 최대 라인 수
) {
    var expanded by remember { mutableStateOf(false) }

    // 실제로 보여줄 텍스트
    val visibleText =
        if (expanded) content else content.take(maxLines * 25) + if (content.length > maxLines * 25) "..." else ""

    Column {
        Text(
            text = visibleText,
            overflow = TextOverflow.Ellipsis,
        )
        if (content.length > maxLines * 25) {
            Row(
                modifier = Modifier.offset(y = (-12).dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { expanded = !expanded }
                ) {
                    Text(if (expanded) "접기" else "더보기", textDecoration = TextDecoration.Underline)
                }
            }

        }
    }
}


@Composable
fun navigateToMeetingScreen(count: String) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.height(36.dp)
    ) {
        Text(
            text = "모임 : $count",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun DetailTitle(title: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Image(
    image: Painter
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color.White)
    )
}

@Composable
fun DetailIcons(painter: Painter) {
    Icon(
        painter = painter,
        contentDescription = null,
        tint = Color.Black,
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp)
    )
}

@Composable
fun FestivalDetailCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .offset(y = (-8).dp),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        shadowElevation = 8.dp
    ) {

    }
}

