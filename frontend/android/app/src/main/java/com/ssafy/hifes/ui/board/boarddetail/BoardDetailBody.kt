package com.ssafy.hifes.ui.board.boarddetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
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
import com.ssafy.hifes.data.model.FestivalTableDto
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.PostItem
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.board.boardcommon.RatingBar
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.theme.PrimaryPink
import myiconpack.User
import java.text.SimpleDateFormat

@Composable
fun BoardDetailBody(postData : PostDto) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        if(postData.postType == PostType.REVIEW.label){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                RatingBar(
                    rating = postData.rating!!,
                    starSize = 30,
                    starColor = PrimaryPink
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = postData.rating.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = PrimaryPink
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Divider()
            Spacer(modifier = Modifier.size(30.dp))
        }
        if(postData.picture!=null){
            Column() {
                AsyncImage(
                    model = postData.picture?:MyIconPack.Imagenotfound,
                    contentDescription = "게시글 이미지",
                    placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound),
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
        Text(
            text = postData.content,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
    }

}

@Composable
@Preview
fun PreviewBoardDetailBody(){
    val festivalTableList = mutableListOf<FestivalTableDto>()
    val formatter = SimpleDateFormat("yyyy.MM.dd")
    val testDate = java.sql.Date(formatter.parse("2023.04.25").time)
    BoardDetailBody(postData = PostDto(
            1,
            1,
            1,
            1,
            "제목",
            "내용",
            "review",
            null,
            null,
            "글쓴이",
            testDate,
            testDate,
            1,
        "https://picsum.photos/600",
            5f
        )
    )
}