package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.main.MainViewModel

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapPrev() {
//    ViewPager(festivalList = null)
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(
    navController: NavController,
    festivalList: MutableList<OrganizedFestivalDto>,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        val pagerState = rememberPagerState(pageCount = {
            festivalList!!.size
        })
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(end = 30.dp),
        ) { index ->
            MapCard(festivalList[index]) { festival ->
                viewModel.getFestivalDetail(festival)
                navController.navigate(HifesDestinations.FESTIVAL_DETAIL)
            }
        }
    }
}