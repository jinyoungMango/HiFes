package com.ssafy.hifes.ui.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.util.CommonUtils
import myiconpack.User
import java.text.SimpleDateFormat

@Composable
fun PostItem(
    postData: PostDto,
    onClick: () -> Unit
) {
    Column {
        Divider(color = LightGrey, thickness = 2.dp)
        Spacer(modifier = Modifier.size(10.dp))

        Row() {
            Spacer(modifier = Modifier.size(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                if(postData.postType==PostType.REVIEW.label){
                    RatingBar(rating = postData.rating!!)
                }
                PostTitle(postData = postData)
                Text(text = postData.content)
                Text(text = CommonUtils.formatSqlDateToString(postData.createdAt))
            }
            AsyncImage(
                model = postData?.picture,
                contentDescription = "게시글 이미지",
                placeholder = rememberVectorPainter(image = MyIconPack.User),
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = LightGrey, thickness = 2.dp)
    }
}

@Composable
@Preview
fun PrevewPostItem() {
    val festivalTableList = mutableListOf<FestivalTableDto>()
    val formatter = SimpleDateFormat("yyyy.MM.dd")
    val testDate = java.sql.Date(formatter.parse("2023.04.25").time)

    Column() {
        PostItem(
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
            onClick = {})
        PostItem(
            postData = PostDto(
                1,
                1,
                1,
                1,
                "리뷰입니다!",
                "축제 재밌어요",
                "review",
                null,
                null,
                "글쓴이",
                testDate,
                testDate,
                1,
                null,
                3.5f
            ),
            onClick = {})
        PostItem(
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
            onClick = {}
        )
        PostItem( //내가 쓴 질문, 공개
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
            onClick = {}
        )
        PostItem(//내가 쓴 질문, 비공개
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
            onClick = {}
        )
        PostItem(//다른사람의 질문, 비공개
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
            onClick = {}
        )
        PostItem(//다른사람의 질문, 공개
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
            onClick = {}
        )

    }
}