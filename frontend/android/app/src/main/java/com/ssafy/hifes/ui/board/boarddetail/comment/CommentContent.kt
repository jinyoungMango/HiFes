package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ssafy.hifes.data.model.CommentDto
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.theme.Grey
import com.ssafy.hifes.ui.theme.LightGrey
import myiconpack.User
import java.text.SimpleDateFormat

@Composable
fun CommentContent(comment: CommentDto) {
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = null, //사용자 이미지 url 필요
                contentDescription = "게시글 이미지",
                placeholder = rememberVectorPainter(image = MyIconPack.User),
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = comment.createdBy, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.size(14.dp))
        Text(text = comment.comment, fontSize = 14.sp)
        Spacer(modifier = Modifier.size(14.dp))
        Text(text = getCommentWriteTime(comment), fontWeight = FontWeight.Light, fontSize = 12.sp, color = Grey)
    }
}

fun getCommentWriteTime(comment: CommentDto): String {
    val formatter = SimpleDateFormat("MM/dd HH:mm")
    return formatter.format(comment.createdAt)
}

@Composable
@Preview
fun PreviewCommentContent() {
    val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm")
    val testDate = java.sql.Date(formatter.parse("2023.04.25 12:03").time)

    CommentContent(
        comment = CommentDto(
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
        )
    )
}