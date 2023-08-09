package com.ssafy.hifes.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChipsSelectable(
    chips: List<String>,
    initStartPosition: Int = 0,
    onChipClick: (index: Int) -> Any
) {
    var selected by rememberSaveable { mutableStateOf(initStartPosition) }

    FlowRow(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        chips.forEachIndexed { index, item ->
            ElevatedFilterChip(
                selected = selected == index,
                onClick = {
                    selected = index
                    onChipClick(index)
                },
                label = {
                    Text(
                        item,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = PrimaryPink,
                    selectedLabelColor = Color.White,
                    containerColor = Color.White
                ),
                elevation = FilterChipDefaults.filterChipElevation(elevation = 8.dp),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = Color.Gray,
                    borderWidth = 0.5.dp
                )
            )
        }
    }

}

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color.LightGray)
    ) {
        Text(
            text = text,
            fontFamily = pretendardFamily, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun HashtagChips(chips: List<String>) {
    LazyRow() {
        items(chips) { item ->
            Chip(text = item)
            Spacer(modifier = Modifier.width(14.dp))
        }
    }
}

@Preview
@Composable
fun ChipPrev() {
    Column {
        ChipsSelectable(listOf("전체", "화장실", "부스", "입구"), 2, {})
        HashtagChips(
            chips = listOf(
                "#6명",
                "대구치맥파티",
                "#치킨",
                "맥주",
                "20대",
                "#6명",
                "대구치맥파티",
                "#치킨",
                "맥주",
                "20대"
            )
        )
    }

}