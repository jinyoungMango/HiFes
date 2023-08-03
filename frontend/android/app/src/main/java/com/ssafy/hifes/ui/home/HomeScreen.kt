package com.ssafy.hifes.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound


@Composable
fun HomeScreen() {
    LazyColumn (
        modifier = Modifier.background(color = Color.White)
//        Modifier
//            .verticalScroll(rememberScrollState())
//            .padding(4.dp)
    ){
        item {
            HomeGreeting("혹시 이 행사에 참여 중이신가요?")
            HomeFestivalImage(R.drawable.ic_launcher_foreground)
            HomeCard(
                title = "대구치맥페스티벌",
                content = "content"
            )
            HomeMiddleText("이 근처에서 축제 열리는 중!")
        }
        items(5) { index ->
            HomeCardWithImage(
                title = "Festival $index",
                content = "content",
                imageRes = R.drawable.ic_launcher_background
            )
        }
    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeCardPrev() {
    HomeScreen()
}

@Composable
fun HomeGreeting(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color(0xFFF11A7B))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.White,
            fontWeight = FontWeight.Bold,
            text = message
        )
    }
}

@Composable
fun HomeFestivalImage(imageResId: Int) {
    AsyncImage(
        model = "https://picsum.photos/1600",
        contentDescription = "게시글 이미지",
        placeholder = rememberVectorPainter(image = MyIconPack.Imagenotfound),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun HomeCard(title: String, content: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        color = Color.White,
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(content, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun HomeMiddleText(message: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = message,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun HomeCardWithImage(title: String, content: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Card Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Text(
                    text = content,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

        }
    }
}

@Composable
fun HomeCardList() {
    LazyColumn {
        items(5) { index ->
            HomeCardWithImage(
                title = "Festival $index",
                content = "content",
                imageRes = R.drawable.ic_launcher_background
            )
        }
    }
}




