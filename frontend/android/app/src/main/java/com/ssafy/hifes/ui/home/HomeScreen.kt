package com.ssafy.hifes.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.DateDto
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.pretendardFamily

private const val TAG = "HomeScreen_하이페스"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {
    val festivalList = viewModel.festivalList.observeAsState()
    val randomFestivalList = viewModel.randomFestivalList.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val location = viewModel.fetchCurrentLocation(context)
        location?.let {
            val curLat = location.latitude
            val curLon = location.longitude
            Log.d(TAG, "HomeScreen: $curLat, $curLon")

            viewModel.getNearFestivalList(curLat, curLon)
            viewModel.getRandomFestivalList()
        }
    }

    val nearestFestival: OrganizedFestivalDto?
    val nearFestivalList: List<OrganizedFestivalDto>
    if (!festivalList.value.isNullOrEmpty()) {
        nearestFestival = festivalList.value!!.first()
        nearFestivalList = festivalList.value!!.drop(1)
    } else {
        nearestFestival = OrganizedFestivalDto(
            0,
            "",
            "",
            "",
            "",
            "",
            DateDto(0, 0, 0),
            DateDto(0, 0, 0),
            0.0,
            0.0,
            0.0,
            0,
            "",
            ""
        )
        nearFestivalList = emptyList()
    }

    Scaffold(topBar = {
        HomeAppBar(navController) { keyword ->
            viewModel.searchFestivalList(keyword)
            navController.navigate(HifesDestinations.HOME_SEARCH)
        }
    }
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(it)
                .verticalScroll(rememberScrollState()),
        ) {
            HomeGreeting(stringResource(id = R.string.home_greeting))
            Column(modifier = Modifier.clickable {
                viewModel.getFestivalInfo(nearestFestival.festivalId)
                navController.navigate(HifesDestinations.FESTIVAL_DETAIL)
            }) {
                HomeFestivalImage(nearestFestival)
                if (nearestFestival != null) {
                    HomeCard(
                        nearestFestival
                    )
                }
            }

            HomeMiddleText(stringResource(id = R.string.home_middle_ment))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // 아이템 사이의 간격
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(nearFestivalList.size) { index ->
                    RoundedImageWithText(
                        modifier = Modifier
                            .width(200.dp)
                            .height(240.dp),
                        nearFestivalList[index]
                    ) { fesData ->
                        viewModel.getFestivalInfo(fesData.festivalId)
                        navController.navigate(HifesDestinations.FESTIVAL_DETAIL)
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                }

            }
            Spacer(modifier = Modifier.size(24.dp))
            HomeMiddleText(stringResource(id = R.string.home_bottom_ment))
            Spacer(modifier = Modifier.size(8.dp))

            randomFestivalList.value?.let { festival ->
                RandomFestivalsRow(festival) { fesData ->
                    viewModel.getFestivalInfo(fesData.festivalId)
                    navController.navigate(HifesDestinations.FESTIVAL_DETAIL)
                }
            }

        }
    }


}

@Composable
fun RandomFestivalsRow(
    festivalList: List<OrganizedFestivalDto>,
    onClick: (OrganizedFestivalDto) -> Unit
) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / LocalDensity.current.density
    val spacerWidth = 8 // Spacer의 너비
    val sidePadding = 16 // start와 end에 추가할 padding

    val availableWidth = screenWidth - 2 * sidePadding - (festivalList.size - 1) * spacerWidth
    val itemWidth = availableWidth / festivalList.size

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = sidePadding.dp, end = sidePadding.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        festivalList.forEachIndexed { index, festival ->
            RoundedImageWithText(
                modifier = Modifier
                    .width(itemWidth.dp)
                    .height(180.dp),
                festival = festival,
                onClick = onClick
            )

            // 마지막 항목이 아닐 때만 Spacer 추가
            if (index < festivalList.size - 1) {
                Spacer(modifier = Modifier.width(spacerWidth.dp))
            }
        }
    }
}


@Composable
fun RoundedImageWithText(
    modifier: Modifier = Modifier,
    festival: OrganizedFestivalDto,
    onClick: (OrganizedFestivalDto) -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(festival) }
    ) {
        AsyncImage(
            model = festival.fesPosterPath,
            contentDescription = "Card Image",
            placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Surface(
            color = Color.Black.copy(alpha = 0.2f),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        ) {}

        Text(
            text = festival.fesTitle,
            color = Color.White,
            style = TextStyle(
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, bottom = 8.dp),
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeCardPrev() {
//    HomeScreen(navController = rememberNavController(), viewModel = MainViewModel())
}

@Composable
fun HomeGreeting(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color(0xFFF11A7B))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.White,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            text = message
        )
    }
}

@Composable
fun HomeFestivalImage(festival: OrganizedFestivalDto) {
    AsyncImage(
        model = festival.fesPosterPath,
        contentScale = ContentScale.Crop,
        contentDescription = "게시글 이미지",
        placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}

@Composable
fun HomeCard(nearestFestival: OrganizedFestivalDto) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(8.dp),
        color = Color.White,
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                nearestFestival.fesTitle,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                nearestFestival.fesOutline,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun HomeMiddleText(message: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp),
        ) {
            Text(
                text = message,
                color = Color.Black,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun HomeCardWithImage(
    festival: OrganizedFestivalDto,
    onClick: (OrganizedFestivalDto) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(10.dp)
            .clickable { onClick(festival) },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(140.dp),
        ) {
            AsyncImage(
                model = festival.fesPosterPath,
                contentDescription = "Card Image",
                placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound),
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column {
                Text(
                    text = festival.fesTitle,
                    color = Color.Black,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = festival.fesOutline,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}





