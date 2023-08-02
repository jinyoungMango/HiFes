package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Comment

@Composable
fun ReComment(onClick: () -> Unit) {

    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = MyIconPack.Comment,
            contentDescription = "WriteReComment"
        )
    }


}