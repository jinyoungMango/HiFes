package com.ssafy.hifes.ui.participatedfest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.NaverGreen
import com.ssafy.hifes.util.CommonUtils
import java.text.SimpleDateFormat

@Composable
fun ParticipatedFestScreen(
    navController: NavController
) {

    val festivalTableList = mutableListOf<FestivalTableDto>()
    val formatter = SimpleDateFormat("yyyy.MM.dd")
    val testDate = java.sql.Date(formatter.parse("2023.04.25").time)
    festivalTableList.apply {
        add(FestivalTableDto(1, 1, 1, "2023 대구 치맥 페스티벌", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 대구 떡볶이 축제", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 서울 뮤직 페스티벌", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "S20 Hong Kong Songkran Music Festival 2023", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 S20 TAIWAN Songkran Festival", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "페스티벌!!", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 제26회 보령머드축제", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 제16회 정남진 장흥물축제", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 싸이 흠뻑쇼 SUMMER SWAG(원주)", "", testDate, testDate))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopWithBack(title = stringResource(R.string.participated_fest_appbar_title))
        Spacer(modifier = Modifier.size(20.dp))
        LazyColumn {
            items(festivalTableList.size) { index ->
                Ticket(
                    title = festivalTableList.get(index).programTitle,
                    date = CommonUtils.formatSqlDateToString(festivalTableList.get(index).startTime)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewParticipatedFestScreen() {
    ParticipatedFestScreen(navController = rememberNavController())
}