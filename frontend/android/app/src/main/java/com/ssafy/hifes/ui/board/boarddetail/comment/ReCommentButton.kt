package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Comment
import com.ssafy.hifes.ui.iconpack.myiconpack.More

@Composable
fun ReCommentButton(onClick: () -> Unit) {

    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = MyIconPack.Comment,
            contentDescription = "WriteReComment",
            tint = Color.Unspecified
        )
    }


}