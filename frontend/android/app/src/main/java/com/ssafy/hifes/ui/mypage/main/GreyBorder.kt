package com.ssafy.hifes.ui.mypage.main

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.theme.Grey

@Composable
fun GreyBorderItem(
    title : String,
    subTitleList : MutableList<String>,
    onClick : () -> Unit
) {

    Column(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = Grey,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(18.dp, 10.dp, 18.dp, 10.dp)
            .fillMaxWidth()
    )
    {
        TitleText(title = title)
        Spacer(modifier = Modifier.size(10.dp))
        LazyColumn{
            items(subTitleList.size){ index ->
                SubTitleText(title = subTitleList.get(index), onClick = onClick)
            }
        }
    }
}

@Preview
@Composable
fun GreyBorderPrev() {
    val context = LocalContext.current
    val testFun = {
        Toast.makeText(context, "", Toast.LENGTH_LONG).show()
    }
    val subTitles= mutableListOf<String>()
    subTitles.add("1번")
    subTitles.add("2번")
    subTitles.add("3번")

    GreyBorderItem (
        title = "테스트",
        subTitleList = subTitles,
        onClick = testFun
    )
}