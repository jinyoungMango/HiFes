package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.CommentDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.common.CustomMenuItem
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Childarrow
import com.ssafy.hifes.ui.theme.LightGrey
import myiconpack.User
import java.text.SimpleDateFormat

@Composable
fun ReComment(commentData: CommentDto, viewModel: BoardViewModel, isFirstReComment: Boolean) {
    var userId = AppPreferences.getUserId()
    val menuItemList : MutableList<CustomMenuItem> = mutableListOf()
    menuItemList.apply {
        add(CustomMenuItem("수정"){})
        add(CustomMenuItem("삭제"){})
    }

    Column (){
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.width(30.dp)) {
                if (isFirstReComment) {
                    Icon(
                        imageVector = MyIconPack.Childarrow,
                        contentDescription = "대댓글",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            CommentContent(comment = commentData)
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                if (userId == commentData.normalUserId.toString()) {
                    CommentMenuButton(menuItemList = menuItemList)
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
        Spacer(modifier = Modifier.size(14.dp))
    }
}

@Composable
@Preview
fun PreviewReComment() {
//    val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm")
//    val testDate = java.sql.Date(formatter.parse("2023.04.25 12:03").time)
//
//    Column() {
//        ReComment(
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
//            BoardViewModel(),
//            true
//        )
//        ReComment(
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
//            BoardViewModel(),
//            false
//        )
//    }
}