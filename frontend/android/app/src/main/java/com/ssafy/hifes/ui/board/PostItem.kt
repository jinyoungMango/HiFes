package com.ssafy.hifes.ui.board

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.board.boardcommon.CustomRatingBar
import com.ssafy.hifes.ui.board.postitemelement.PostContent
import com.ssafy.hifes.ui.board.postitemelement.PostImage
import com.ssafy.hifes.ui.board.postitemelement.PostTitle
import com.ssafy.hifes.ui.theme.LightGrey
import com.ssafy.hifes.ui.theme.pretendardFamily
import com.ssafy.hifes.util.CommonUtils
import java.text.SimpleDateFormat

@Composable
fun PostItem(
    postData: PostDto,
    userDataId : Int,
    onClick: (PostDto) -> Unit
) {
    Column (modifier = Modifier.clickable { onClick(postData) }){
        Spacer(modifier = Modifier.size(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.size(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                if(postData.postType== PostType.REVIEW.label){
                    CustomRatingBar(
                        rating = postData.rating!!,
                        starSize = 14,
                        starColor = Color.Black
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                }
                PostTitle(postData = postData, userDataId)
                Spacer(modifier = Modifier.size(4.dp))
                PostContent(postData = postData, userDataId)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = CommonUtils.formatSqlDateToString(postData.createdAt), fontSize = 14.sp, fontFamily = pretendardFamily, fontWeight = FontWeight.Normal)
            }
            Spacer(modifier = Modifier.size(10.dp))
            PostImage(postData = postData, userDataId = userDataId)
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
            1,
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
            1,
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
            1,
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
            1,
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
            1,
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
            1,
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
            1,
            onClick = {}
        )

    }
}