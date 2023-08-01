package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.animation.AnimationUtils.lerp
import kotlin.math.absoluteValue

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapPrev() {
    ViewPager()
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        val pagerState = rememberPagerState(pageCount = {
            10
        })
        HorizontalPager(state = pagerState,
            contentPadding = PaddingValues(end = 30.dp),) { page ->
            Card(
                Modifier
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
//                        alpha = lerp(
//                            0.5f,
//                            1f,
//                            1f - pageOffset.coerceIn(0f, 1f)
//                        )
                    },
                backgroundColor = Color.White.copy(alpha = 0.0f),
                elevation = 0.dp
            ) {
                MapScreen()
            }
        }
    }
}