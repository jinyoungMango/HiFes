package com.ssafy.hifes.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.pretendardFamily

private const val TAG = "HomeScreen_하이페"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {
    val festivalList = viewModel.festivalList.observeAsState()

    val nearestFestival: OrganizedFestivalDto?
    val nearFestivalList: List<OrganizedFestivalDto>
    if (!festivalList.value.isNullOrEmpty()) {
        nearestFestival = festivalList.value!!.first()
        nearFestivalList = festivalList.value!!.drop(1)
    } else {
        nearestFestival = OrganizedFestivalDto()
        nearFestivalList = emptyList()
    }

    Scaffold(topBar = {
        HomeAppBar(navController)
    },
        content = {
            LazyColumn(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(it),
            ) {
                item {
                    HomeGreeting(stringResource(id = R.string.home_greeting))
                    Column(modifier = Modifier.clickable {
                        viewModel.getFestivalDetail(nearestFestival)
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
                }
                items(nearFestivalList.size) { festival ->
                    HomeCardWithImage(
                        nearFestivalList[festival]
                    ) { fesData ->
                        viewModel.getFestivalDetail(fesData)
                        navController.navigate(HifesDestinations.FESTIVAL_DETAIL)
                    }
                }
            }
        })


}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeCardPrev() {
    HomeScreen(navController = rememberNavController(), viewModel = MainViewModel())
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
            .height(220.dp).padding(8.dp),
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
            .height(50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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





