package com.ssafy.hifes.ui.board

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Postlocked
import com.ssafy.hifes.ui.iconpack.myiconpack.Postunlocked
import com.ssafy.hifes.ui.theme.Grey
import com.ssafy.hifes.ui.theme.LightGrey
import java.text.SimpleDateFormat

@Composable
fun PostTitle(
    postData: PostDto
) {
    var userDataId = 1 //추후 viewModel에서 지금 사용자를 구분하는 값을 저장했다가 가져와 사용
    val resultTitle = getTitle(postData, LocalContext.current, userDataId)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = resultTitle, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        if (postData.normalUserId == userDataId && postData.postType == PostType.ASK.label) {
            Spacer(modifier = Modifier.size(8.dp))
            if (postData.hidden != null && postData.hidden!!) {
                Icon(imageVector = MyIconPack.Postlocked, contentDescription = "잠긴 글", tint = Grey)
            } else {
                Icon(imageVector = MyIconPack.Postunlocked, contentDescription = "열린 글", tint = Grey)
            }
        }
    }
}

fun getTitle(postData: PostDto, context: Context, userDataId: Int): String {
    var resultTitle = ""
    when (postData.postType) {
        PostType.ASK.label -> {
            if (userDataId == postData.normalUserId) {
                resultTitle = postData.title
            } else {
                if (postData.hidden != null && postData.hidden!!) {
                    resultTitle = context.getString(R.string.board_hidden_post_title)
                } else {
                    resultTitle = postData.title
                }
            }
        }

        else -> {
            resultTitle = postData.title
        }
    }
    return resultTitle
}

@Composable
@Preview
fun PreviewPostTitle() {
    val festivalTableList = mutableListOf<FestivalTableDto>()
    val formatter = SimpleDateFormat("yyyy.MM.dd")
    val testDate = java.sql.Date(formatter.parse("2023.04.25").time)
    Column() {
        PostTitle(
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
            )
        )
        PostTitle( //내가 쓴 질문, 공개
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
            )
        )
        PostTitle(//내가 쓴 질문, 비공개
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
            )
        )
        PostTitle(//다른사람의 질문, 비공개
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
            )
        )
        PostTitle(//다른사람의 질문, 공개
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
            )
        )
    }

}
