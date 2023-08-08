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
import com.ssafy.hifes.data.model.OrganizedFestivalDto

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapPrev() {
//    ViewPager(festivalList = null)
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(festivalList: MutableList<OrganizedFestivalDto>, modifier: Modifier = Modifier) {
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
//            Card(
//                backgroundColor = Color.White.copy(alpha = 0.0f),
//                elevation = 0.dp
//            ) {
            MapScreenContent(festivalList[index])

        }
    }
}