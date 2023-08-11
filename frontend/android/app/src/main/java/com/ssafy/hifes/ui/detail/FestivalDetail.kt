package com.ssafy.hifes.ui.detail

import NavigationItem
import android.content.ActivityNotFoundException
import android.content.Context
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
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
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import com.kakao.sdk.template.model.Button
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.Link
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import com.ssafy.hifes.BuildConfig
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.map.StarScore
import com.ssafy.hifes.ui.theme.pretendardFamily
import com.ssafy.hifes.util.CommonUtils
import com.ssafy.hifes.util.CommonUtils.formatSqlDateToString


private const val TAG = "FestivalDetail_하이페스"
@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun FestivalDetail(navController: NavHostController, viewModel: MainViewModel) {
    val selectedFestival = viewModel.selectedFestival.observeAsState()
    val context = LocalContext.current
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
                        DetailIcons(painterResource(R.drawable.icon_share)) {
                            kakaoShare(festivalData, context)
                        }
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
                            content1 = CommonUtils.formatFestivalDateToString(festivalData.fesStartDate),
                            content2 = CommonUtils.formatFestivalDateToString(festivalData.fesEndDate)
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
//    FestivalDetail(navController = rememberNavController(), MainViewModel())
}

fun kakaoShare(festival: OrganizedFestivalDto, context: Context) {
    val apiKey = BuildConfig.API_KEY
    val defaultFeed = FeedTemplate(
        content = Content(
            title = festival.fesTitle,
            description = festival.fesOutline,
            imageUrl = festival.fesPosterPath,
            link = Link(
                webUrl ="kakao$apiKey://kakaolink",
                mobileWebUrl = "kakao$apiKey://kakaolink"
            )
        ),
        buttons = listOf(
            Button(
                "웹으로 보기",
                Link(
                    webUrl = "kakao$apiKey://kakaolink",
                    mobileWebUrl = "kakao$apiKey://kakaolink"
                )
            ),
            Button(
                "앱으로 보기",
                Link(
                    webUrl = "kakao$apiKey://kakaolink",
                    mobileWebUrl = "kakao$apiKey://kakaolink",
                    androidExecutionParams = mapOf("key1" to "value1", "key2" to "value2"),
                    iosExecutionParams = mapOf("key1" to "value1", "key2" to "value2")
                )
            )
        )
    )

    // 카카오톡 설치여부 확인
    if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
        // 카카오톡으로 카카오톡 공유 가능
        ShareClient.instance.shareDefault(context, defaultFeed) { sharingResult, error ->
            if (error != null) {
                Log.e(TAG, "카카오톡 공유 실패", error)
            }
            else if (sharingResult != null) {
                Log.d(TAG, "카카오톡 공유 성공 ${sharingResult.intent}")
                context.startActivity(sharingResult.intent)

                // 카카오톡 공유에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                Log.w(TAG, "Warning Msg: ${sharingResult.warningMsg}")
                Log.w(TAG, "Argument Msg: ${sharingResult.argumentMsg}")
            }
        }
    } else {
        // 카카오톡 미설치: 웹 공유 사용 권장
        // 웹 공유 예시 코드
        val sharerUrl = WebSharerClient.instance.makeDefaultUrl(defaultFeed)

        // CustomTabs으로 웹 브라우저 열기

        // 1. CustomTabsServiceConnection 지원 브라우저 열기
        // ex) Chrome, 삼성 인터넷, FireFox, 웨일 등
        try {
            KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
        } catch(e: UnsupportedOperationException) {
            // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
        }

        // 2. CustomTabsServiceConnection 미지원 브라우저 열기
        // ex) 다음, 네이버 등
        try {
            KakaoCustomTabsClient.open(context, sharerUrl)
        } catch (e: ActivityNotFoundException) {
            // 디바이스에 설치된 인터넷 브라우저가 없을 때 예외처리
        }
    }
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

