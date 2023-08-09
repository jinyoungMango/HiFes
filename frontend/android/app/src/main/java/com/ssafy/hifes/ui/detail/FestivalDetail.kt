package com.ssafy.hifes.ui.detail

import NavigationItem
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.map.StarScore
import com.ssafy.hifes.ui.theme.pretendardFamily
import com.ssafy.hifes.util.CommonUtils.formatSqlDateToString


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun FestivalDetail(navController: NavHostController, viewModel: MainViewModel) {
    val selectedFestival = viewModel.selectedFestival.observeAsState()
    if (selectedFestival.value != null) {
        val festivalData = selectedFestival.value
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {
            if (festivalData != null) {
                Box {
                    AsyncImage(
                        model = festivalData.fesPosterPath,
                        contentDescription = "Poster Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(288.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound)
                    )
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .padding(top = 4.dp)
                    ) {
                        DetailIcons(painterResource(R.drawable.icon_map)) {
                            viewModel.updateMapTypeFestival()
                            navController.navigate(
                                NavigationItem.Map.screenRoute
                            )
                        }
                        DetailIcons(painterResource(R.drawable.icon_board)) {
                            navController.navigate(
                                HifesDestinations.BOARD_ROUTE
                            )
                        }
                        DetailIcons(painterResource(R.drawable.icon_share)) {}
                    }
                }
                Column {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-12).dp),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        shadowElevation = 2.dp
                    ) {
                        Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                            Spacer(modifier = Modifier.size(4.dp))
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, end = 8.dp, bottom = 6.dp)
                            ) {
                                navigateToMeetingScreen("12개", navController) // 추후 서버에서 가져옴
                            }
                            DetailTitle(festivalData.fesTitle)

                            StarScore(score = 4.0)


                            Spacer(modifier = Modifier.size(12.dp))
                            DetailContent(festivalData.fesOutline)

                        }
                    }
                    Column(
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                    ) {
                        Spacer(modifier = Modifier.size(12.dp))
                        DetailCommonContent(
                            title = "일정",
                            content1 = formatSqlDateToString(festivalData.fesStartDate),
                            content2 = formatSqlDateToString(festivalData.fesEndDate)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        DetailCommonContent(title = "장소", address = "주소")
                        Spacer(modifier = Modifier.size(12.dp))
                        FestivalLocation(
                            festivalData.fesLatitude,
                            festivalData.fesLongitude,
                            festivalData.fesTitle
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        // 추후 서버에서 가져온 데이터로 변경
                        DetailCommonContent(
                            title = "주최",
                            content1 = "대구광역시",
                            content2 = "053 - 248 - 9998"
                        )
                        Spacer(modifier = Modifier.size(24.dp))
                    }
                }
            }

        }
    }


}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun FestivalDetailPrev() {
    FestivalDetail(navController = rememberNavController(), MainViewModel())
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun FestivalLocation(lat: Double, lng: Double, title: String) {
    val festivalLatLng = LatLng(lat, lng)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(festivalLatLng, 14.0)
    }
    NaverMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = festivalLatLng),
            captionText = title,
            captionTextSize = 14.sp,
            icon = OverlayImage.fromResource(R.drawable.icon_marker),
        )
    }
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
                    Text(
                        if (expanded) "접기" else "더보기",
                        textDecoration = TextDecoration.Underline
                    )
                }
            }


        }
    }

}


@Composable
fun navigateToMeetingScreen(count: String, navController: NavController) {
    OutlinedButton(
        onClick = {
            navController.navigate(NavigationItem.Group.screenRoute)
        },
        modifier = Modifier.height(36.dp),
        border = BorderStroke(0.3.dp, Color.Gray)
    ) {
        Text(
            text = "모임 : $count",
            fontFamily = pretendardFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
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
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
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
            .height(150.dp)
            .background(color = Color.White)
    )
}

@Composable
fun DetailIcons(painter: Painter, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(6.dp)
            .size(44.dp)
            .shadow(elevation = 4.dp, shape = CircleShape, ambientColor = Color.LightGray),
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White),
        content = {
            Icon(
                painter = painter,
                contentDescription = "painter",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(34.dp)
                    .padding(4.dp)
            )
        }
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

