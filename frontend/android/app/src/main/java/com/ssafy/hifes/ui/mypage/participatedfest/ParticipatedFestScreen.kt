package com.ssafy.hifes.ui.mypage.participatedfest

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.StampMissionDto
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.mypage.MyPageViewModel
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.util.CommonUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ParticipatedFestScreen(
    navController: NavController,
    viewModel: MyPageViewModel
) {
    var context = LocalContext.current
    var participatedFest = viewModel.participatedFestival.observeAsState()
    var errMsgParticipatedFestList = viewModel.errMsgParticipatedFestList.observeAsState()

    var skipHalfExpanded by remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit){
        viewModel.getParticipatedFestival()
    }

    errMsgParticipatedFestList.value?.getContentIfNotHandled()?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }

    if (participatedFest.value != null) {
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
                TopWithBack(
                    navController,
                    title = stringResource(R.string.participated_fest_appbar_title)
                )
                Spacer(modifier = Modifier.size(20.dp))
                LazyColumn {
                    items(participatedFest.value!!.size) { index ->
                        Ticket(
                            title = participatedFest.value!!.get(index).fesTitle,
                            date = CommonUtils.formatFestivalDateToString(
                                participatedFest.value!!.get(index).participateTime.date
                            ),
                            onClick = { scope.launch { state.show() } }
                        )
                    }
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
   // ParticipatedFestScreen(navController = rememberNavController())
}