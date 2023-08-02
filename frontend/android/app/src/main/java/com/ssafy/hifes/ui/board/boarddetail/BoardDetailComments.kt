package com.ssafy.hifes.ui.board.boarddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ssafy.hifes.data.model.CommentDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boarddetail.comment.Comment
import com.ssafy.hifes.ui.board.boarddetail.comment.ReComment
import java.text.SimpleDateFormat

@Composable
fun BoardDetailComments(
    viewModel: BoardViewModel
) {
    val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm")
    val testDate = java.sql.Date(formatter.parse("2023.04.25 12:03").time)

    val commentList: MutableList<CommentDto> = mutableListOf()
    commentList.apply {
        add(
            CommentDto(commentId = 1, normalUserId = 1, postId = 1, festivalId = 1, hostId = 1, comment = "댓글내용", createdAt = testDate, createdBy = "사용자 닉네임", updatedAt = testDate, depth = false)
        )
        add(
            CommentDto(commentId = 1, normalUserId = 2, postId = 1, festivalId = 1, hostId = 1, comment = "댓글내용", createdAt = testDate, createdBy = "사용자 닉네임", updatedAt = testDate, depth = false)
        )
    }
    val reCommentList: MutableList<CommentDto> = mutableListOf()
    reCommentList.apply {
        add(
            CommentDto(commentId = 1, normalUserId = 1, postId = 1, festivalId = 1, hostId = 1, comment = "댓글내용", createdAt = testDate, createdBy = "사용자 닉네임", updatedAt = testDate, depth = true)
        )
        add(
            CommentDto(commentId = 1, normalUserId = 1, postId = 1, festivalId = 1, hostId = 1, comment = "댓글내용", createdAt = testDate, createdBy = "사용자 닉네임", updatedAt = testDate, depth = true)
        )
    }
    Column {
        commentList.forEach(){item->
            Comment(commentData = item, viewModel = viewModel)
        }
        reCommentList.forEachIndexed(){index, item->
            ReComment(commentData = item, viewModel = viewModel, isFirstReComment = index==0)
        }
    }
}