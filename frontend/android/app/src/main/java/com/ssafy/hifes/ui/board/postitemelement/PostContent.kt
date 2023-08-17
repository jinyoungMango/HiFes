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
import com.ssafy.hifes.data.model.DateDto
import com.ssafy.hifes.data.model.DateTime
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.data.model.TimeDto
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.theme.pretendardFamily

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
            if (userDataId == postData.createdBy) {
                resultTitle = postData.content
            } else {
                if (postData.isHidden != null && postData.isHidden!!) {
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
    Column() {
        PostContent(
            postData = PostDto(
                1,
                1,
                "글쓴이",
                "제목",
                "notice",
                1,
                1,
                DateTime(DateDto(2022,4,25), TimeDto(0,0,0, 0)),
                "내용",
                true,
                null,
                null,
                5f
            ),
            1
        )
        PostContent( //내가 쓴 질문, 공개
            postData = PostDto(
                1,
                1,
                "글쓴이",
                "제목",
                "ask",
                1,
                1,
                DateTime(DateDto(2022,4,25), TimeDto(0,0,0, 0)),
                "내용",
                false,
                null,
                null,
                5f
            ),
            1
        )
        PostContent(//내가 쓴 질문, 비공개
            postData = PostDto(
                1,
                1,
                "글쓴이",
                "제목",
                "ask",
                1,
                1,
                DateTime(DateDto(2022,4,25), TimeDto(0,0,0, 0)),
                "내용",
                true,
                null,
                null,
                5f
            ),
            1
        )
        PostContent(//다른사람의 질문, 비공개
            postData = PostDto(
                1,
                2,
                "글쓴이",
                "제목",
                "ask",
                1,
                1,
                DateTime(DateDto(2022,4,25), TimeDto(0,0,0, 0)),
                "내용",
                true,
                null,
                null,
                5f
            ),
            1
        )
        PostContent(//다른사람의 질문, 공개
            postData = PostDto(
                1,
                2,
                "글쓴이",
                "제목",
                "ask",
                1,
                1,
                DateTime(DateDto(2022,4,25), TimeDto(0,0,0, 0)),
                "내용",
                false,
                null,
                null,
                5f
            ),
            1
        )
    }

}
