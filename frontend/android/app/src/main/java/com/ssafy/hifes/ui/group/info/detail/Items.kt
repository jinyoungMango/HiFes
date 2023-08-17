package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ssafy.hifes.data.model.Member
import com.ssafy.hifes.data.model.SharedPicDto
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfoundmedium
import com.ssafy.hifes.ui.theme.pretendardFamily

// https://ichef.bbci.co.uk/news/640/cpsprodpb/E172/production/_126241775_getty_cats.png

data class User(
    val url: String,
    val name: String
)

data class Img(
    val url: String
)

private const val TAG = "Items"

@Composable
fun GroupMember(member: Member) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = member.userProfilePic,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = ColorPainter(Color.Green),
            modifier = Modifier
                .size(68.dp)
                .clip(CircleShape),
            error = rememberVectorPainter(MyIconPack.Imagenotfoundmedium)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = member.userNickname,
            fontSize = 14.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun GroupMemberRow(groupMember: List<Member>) {
    Column {
        Text(
            text = "멤버",
            fontSize = 16.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(groupMember) { item ->
                GroupMember(item)
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun GroupPictureRow(img: List<SharedPicDto>) {
    Column {
        Text(
            text = "사진",
            fontSize = 16.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(img) { item ->
                AsyncImage(
                    model = item.sharedPic,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(Color.Green),
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    error = rememberVectorPainter(MyIconPack.Imagenotfound)
                )
                Spacer(modifier = Modifier.width(14.dp))
            }
        }
    }
}

@Composable
fun GroupPictureGrid(groupViewModel: GroupViewModel) {
    val groupImages by groupViewModel.groupImages.observeAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), modifier = Modifier.padding(8.dp),
    ) {
        items(groupImages!!) { item ->
            AsyncImage(
                model = item.sharedPic,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.Green),
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))

            )
        }
    }
}

@Preview
@Composable
fun GroupMemeberPrev() {
//    val user = User(
//        url = "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
//        name = "L"
//    )
//    val img = Img(
//        url = "https://fastly.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U"
//    )
////    GroupMember(user)
//    Column() {
//        GroupPictureRow(img = listOf(img, img, img, img, img, img))
//        Spacer(modifier = Modifier.height(24.dp))
//        GroupMemberRow(groupMember = listOf(user, user, user, user, user, user))
//        Spacer(modifier = Modifier.height(24.dp))
//        GroupPictureGrid(img = listOf(img, img, img, img, img, img, img, img, img, img, img, img))
//    }
}