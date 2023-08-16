package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.CommentDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.common.CustomMenuItem
import java.text.SimpleDateFormat

@Composable
fun Comment(commentData: CommentDto, viewModel: BoardViewModel) {
    var userId = AppPreferences.getUserId()
    val menuItemList : MutableList<CustomMenuItem> = mutableListOf()
    menuItemList.apply {
        add(CustomMenuItem("수정"){})
        add(CustomMenuItem("삭제"){})
    }
    if(userId != null){
        Column {
            Row {
                Spacer(modifier = Modifier.size(10.dp))
                CommentContent(comment = commentData)
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.End
                ) {
                    if (userId != commentData.normalUserId.toString()) {
                        ReCommentButton ({})
                    } else {
                        CommentMenuButton(menuItemList = menuItemList)
                    }

                }
                Spacer(modifier = Modifier.size(10.dp))
            }
            Spacer(modifier = Modifier.size(14.dp))
        }
    }


}

@Composable
@Preview
fun PreviewComment() {
//    val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm")
//    val testDate = java.sql.Date(formatter.parse("2023.04.25 12:03").time)
//
//    Column() {
//        Comment(
//            CommentDto(
//                commentId = 1,
//                normalUserId = 1,
//                postId = 1,
//                festivalId = 1,
//                hostId = 1,
//                comment = "댓글내용",
//                createdAt = testDate,
//                createdBy = "사용자 닉네임",
//                updatedAt = testDate,
//                depth = false
//            ),
//            BoardViewModel()
//        )
//        Comment(
//            CommentDto(
//                commentId = 1,
//                normalUserId = 2,
//                postId = 1,
//                festivalId = 1,
//                hostId = 1,
//                comment = "댓글내용",
//                createdAt = testDate,
//                createdBy = "사용자 닉네임",
//                updatedAt = testDate,
//                depth = false
//            ),
//            BoardViewModel()
//        )
//    }

}