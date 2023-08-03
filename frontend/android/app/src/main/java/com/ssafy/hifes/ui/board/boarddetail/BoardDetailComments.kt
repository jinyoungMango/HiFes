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
    viewModel: BoardViewModel,
    commentList : MutableList<CommentDto>,
    reCommentList : MutableList<CommentDto>?
) {

    Column() {
        commentList.forEach {
            Comment(commentData = it, viewModel = viewModel)
        }

        reCommentList?.forEachIndexed { index, commentDto ->
            ReComment(commentData = commentDto, viewModel = viewModel, isFirstReComment = index==0)
        }
    }

}