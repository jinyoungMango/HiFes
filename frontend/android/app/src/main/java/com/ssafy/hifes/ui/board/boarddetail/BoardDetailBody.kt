package com.ssafy.hifes.ui.board.boarddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.data.model.PostDto

@Composable
fun BoardDetailBody(postData : PostDto) {
    Column {
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = postData.content, fontSize = 14.sp)
    }

}