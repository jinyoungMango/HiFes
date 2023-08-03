package com.ssafy.hifes.ui.participatedfest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.StampMissionDto
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.util.CommonUtils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterialApi::class)
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
        add(
            FestivalTableDto(
                1,
                1,
                1,
                "S20 Hong Kong Songkran Music Festival 2023",
                "",
                testDate,
                testDate
            )
        )
        add(FestivalTableDto(1, 1, 1, "2023 S20 TAIWAN Songkran Festival", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "페스티벌!!", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 제26회 보령머드축제", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 제16회 정남진 장흥물축제", "", testDate, testDate))
        add(FestivalTableDto(1, 1, 1, "2023 싸이 흠뻑쇼 SUMMER SWAG(원주)", "", testDate, testDate))
    }
    var skipHalfExpanded by remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            BottomSheetScreenElement()
        },
        sheetShape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
        sheetGesturesEnabled = false
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopWithBack(navController, title = stringResource(R.string.participated_fest_appbar_title))
            Spacer(modifier = Modifier.size(20.dp))
            LazyColumn {
                items(festivalTableList.size) { index ->
                    Ticket(
                        title = festivalTableList.get(index).programTitle,
                        date = CommonUtils.formatSqlDateToString(festivalTableList.get(index).startTime),
                        onClick = { scope.launch { state.show() } }
                    )
                }
            }
        }
    }
}


@Composable
fun BottomSheetScreenElement() {
    val stampList = mutableListOf<StampMissionDto>()
    stampList.apply {
        add(StampMissionDto(1, 1, 1, "미션 타이틀", "미션 아웃라인", 1.0, 1.0))
        add(StampMissionDto(1, 1, 1, "미션 타이틀", "미션 아웃라인", 1.0, 1.0))
        add(StampMissionDto(1, 1, 1, "미션 타이틀", "미션 아웃라인", 1.0, 1.0))
        add(StampMissionDto(1, 1, 1, "미션 타이틀", "미션 아웃라인", 1.0, 1.0))
    }
    val stampCount = 10//한 행사에서 받을 수 있는 스탬프의 총 량, 나중에 서버에서 받아올것
    val stampStateList: MutableList<Boolean> = mutableListOf()

    //총 10개 중 4개 스탬프를 받은 상황을 가정함
    for (stamp: Int in 0 until stampCount) {
        if (stamp < stampList.size) {
            stampStateList.add(true)
        } else {
            stampStateList.add(false)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = stringResource(id = R.string.participated_fest_stamp_list_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.size(20.dp))
        Divider(color = LightGrey, thickness = 2.dp)
        Spacer(modifier = Modifier.size(10.dp))
        Card(
            elevation = 4.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(stampStateList.size) { index ->
                        Stamp(state = stampStateList[index])
                    }
                }
            }

        }
        Spacer(modifier = Modifier.size(20.dp))
    }

}


@Composable
@Preview
fun PreviewParticipatedFestScreen() {
    ParticipatedFestScreen(navController = rememberNavController())
}