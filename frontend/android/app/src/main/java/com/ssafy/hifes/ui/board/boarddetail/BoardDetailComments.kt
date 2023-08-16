package com.ssafy.hifes.ui.board.boarddetail

import androidx.compose.foundation.lazy.LazyListScope
import com.ssafy.hifes.data.model.CommentDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boarddetail.comment.Comment


fun LazyListScope.BoardDetailComments(
    viewModel: BoardViewModel,
    commentList: List<CommentDto>,
) {

    commentList.forEach {
        Comment(commentData = it, viewModel = viewModel)
    }

}