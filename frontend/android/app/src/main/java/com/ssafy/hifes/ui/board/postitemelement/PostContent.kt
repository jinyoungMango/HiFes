package com.ssafy.hifes.ui.board.postitemelement

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.theme.pretendardFamily
import java.text.SimpleDateFormat

@Composable
fun PostContent(
    postData: PostDto,
    userDataId: Int
) {
    val resultContent = getContent(postData, LocalContext.current, userDataId)
    Text(
        text = resultContent,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    )
}

fun getContent(postData: PostDto, context: Context, userDataId: Int): String {
    var resultTitle = ""
    when (postData.postType) {
        PostType.ASK.label -> {
            if (userDataId == postData.normalUserId) {
                resultTitle = postData.content
            } else {
                if (postData.hidden != null && postData.hidden!!) {
                    resultTitle = context.getString(R.string.board_hidden_post_title)
                } else {
                    resultTitle = postData.content
                }
            }
        }

        else -> {
            resultTitle = postData.content
        }
    }
    return resultTitle
}

@Composable
@Preview
fun PreviewPostContent() {
    val festivalTableList = mutableListOf<FestivalTableDto>()
    val formatter = SimpleDateFormat("yyyy.MM.dd")
    val testDate = java.sql.Date(formatter.parse("2023.04.25").time)
    Column() {
        PostContent(
            postData = PostDto(
                1,
                1,
                1,
                1,
                "제목",
                "내용",
                "notification",
                null,
                null,
                "글쓴이",
                testDate,
                testDate,
                1,
                null,
                5f
            ),
            1
        )
        PostContent( //내가 쓴 질문, 공개
            postData = PostDto(
                1,
                1,
                1,
                1,
                "내가 쓴 질문, 공개",
                "내용",
                "ask",
                false,
                null,
                "글쓴이",
                testDate,
                testDate,
                1,
                null,
                5f
            ),
            1
        )
        PostContent(//내가 쓴 질문, 비공개
            postData = PostDto(
                1,
                1,
                1,
                1,
                "내가 쓴 질문, 비공개",
                "내용",
                "ask",
                true,
                null,
                "글쓴이",
                testDate,
                testDate,
                1,
                null,
                5f
            ),
            1
        )
        PostContent(//다른사람의 질문, 비공개
            postData = PostDto(
                1,
                1,
                1,
                2,
                "다른사람의 질문, 비공개",
                "내용",
                "ask",
                true,
                null,
                "글쓴이",
                testDate,
                testDate,
                1,
                null,
                5f
            ),
            1
        )
        PostContent(//다른사람의 질문, 공개
            postData = PostDto(
                1,
                1,
                1,
                2,
                "다른사람의 질문, 공개",
                "내용",
                "ask",
                false,
                null,
                "글쓴이",
                testDate,
                testDate,
                1,
                null,
                5f
            ),
            1
        )
    }

}
