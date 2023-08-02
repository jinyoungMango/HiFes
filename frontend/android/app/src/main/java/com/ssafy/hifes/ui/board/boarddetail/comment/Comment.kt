package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.data.model.CommentDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Comment
import com.ssafy.hifes.ui.iconpack.myiconpack.More
import com.ssafy.hifes.ui.theme.Grey
import java.text.SimpleDateFormat

@Composable
fun Comment(commentData: CommentDto, viewModel: BoardViewModel) {
    Row(modifier = Modifier.fillMaxWidth()) {
        CommentContent(comment = commentData)
        Box(
            modifier = Modifier.weight(1f).padding(16.dp),
            contentAlignment = Alignment.CenterEnd,
        ) {
            if (viewModel.userDataId != commentData.normalUserId) {
                ReComment ({})
            } else {
                CommentMenu(menuItemList = mutableListOf())
            }

        }
    }
}

@Composable
@Preview
fun PreviewComment() {
    val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm")
    val testDate = java.sql.Date(formatter.parse("2023.04.25 12:03").time)

    Column() {
        Comment(
            CommentDto(
                commentId = 1,
                normalUserId = 1,
                postId = 1,
                festivalId = 1,
                hostId = 1,
                comment = "댓글내용",
                createdAt = testDate,
                createdBy = "사용자 닉네임",
                updatedAt = testDate,
                depth = false
            ),
            BoardViewModel()
        )
        Comment(
            CommentDto(
                commentId = 1,
                normalUserId = 2,
                postId = 1,
                festivalId = 1,
                hostId = 1,
                comment = "댓글내용",
                createdAt = testDate,
                createdBy = "사용자 닉네임",
                updatedAt = testDate,
                depth = false
            ),
            BoardViewModel()
        )
    }

}