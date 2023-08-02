package com.ssafy.hifes.ui.board.boarddetail

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.theme.Grey
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.util.CommonUtils

@Composable
fun BoardDetailHead(postData: PostDto) {
    Column {
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "닉네임",//서버에서 게시글 정보 가져올때 닉네임 함께 가져오도록 해야한다
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = postData.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = getBoardDetailHeaderDateSeenTimeString(postData, LocalContext.current),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Grey
        )
        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = LightGrey, thickness = 2.dp)
    }
}

fun getBoardDetailHeaderDateSeenTimeString(postData: PostDto, context: Context): String {
    return "${context.getString(R.string.board_detail_regist)}: ${
        CommonUtils.formatSqlDateToString(
            postData.createdAt
        )
    } | ${context.getString(R.string.board_detail_modify)}: ${
        CommonUtils.formatSqlDateToString(
            postData.updatedAt
        )
    } | ${context.getString(R.string.board_detail_seen_time)}: ${postData.seenTimes}"
}