package com.example.hifes.ui.group.info

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hifes.ui.theme.PrimaryPink

@Composable
fun GroupTab(modifier: Modifier, selected: Int, setSelected: (Int) -> Unit) {

    CustomTab(
        items = listOf("정보", "사진", "채팅"),
        selectedItemIndex = selected,
        onClick = setSelected,
    )
}

@Preview
@Composable
fun GroupPrev() {
    var selectedTab by remember {mutableStateOf(0)}
    // LocalConfiguration을 이용하여 현재 환경 정보를 가져옵니다.
    val configuration = LocalConfiguration.current

    // 화면의 가로 길이를 반환합니다.
    val tabWidth = (rememberUpdatedState(configuration.screenWidthDp).value / 3).dp

    GroupTab(modifier = Modifier, selected = selectedTab, setSelected = {selectedTab = it})

}

// indicator로 사용할 박스
@Composable
private fun MyTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(
                width = indicatorWidth,
            )
            .offset(
                x = indicatorOffset,
            )
            .clip(
                shape = RoundedCornerShape(6.dp),
            )
            .background(
                color = indicatorColor,
            )
    )
}

// 각 탭에 들어갈 텍스트
@Composable
private fun MyTabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            White
        } else {
            Black
        },
        animationSpec = tween(easing = LinearEasing)
    )
    Box(modifier = Modifier.fillMaxHeight()) {
        Text(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp),)
                .clickable {
                    onClick()
                }
                .width(tabWidth)
                .padding(
                    vertical = 8.dp,
                    horizontal = 12.dp,
                )
                .align(Alignment.Center)
            ,

            text = text,
            color = tabTextColor,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun CustomTab(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = (LocalConfiguration.current.screenWidthDp/3).dp, // 길이 변경
    onClick: (index: Int) -> Unit,
) {

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing, durationMillis = 50),  // 속도 조절
    )

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(6.dp),)
            .background(Color.White)
            .height(intrinsicSize = IntrinsicSize.Min)
            .fillMaxWidth()
            .border(1.dp, PrimaryPink, RoundedCornerShape(6.dp))
    ) {
        MyTabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = PrimaryPink,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(CircleShape)
//                .border(2.dp, PrimaryPink, RoundedCornerShape(6.dp))
                .height(48.dp),
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                MyTabItem(
                    isSelected = isSelected,
                    onClick = {
                        onClick(index)
                    },
                    tabWidth = tabWidth,
                    text = text,
                )
            }
        }
    }
}