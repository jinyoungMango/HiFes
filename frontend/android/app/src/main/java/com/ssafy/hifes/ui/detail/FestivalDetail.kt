package com.ssafy.hifes.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.R


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeCardPrev() {
    Column {
        Box {
            Image(image = painterResource(R.drawable.baseline_arrow_back_ios_24))
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                DetailIcons(painterResource(R.drawable.icon_board))
                DetailIcons(painterResource(R.drawable.icon_map))
            }
        }
        HomeCard()
    }

}

@Composable
fun Image(
    image: Painter
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color.Green)
    )
}

@Composable
fun DetailIcons(painter: Painter) {
    Icon(
        painter = painter,
        contentDescription = null,
        tint = Color.Black,
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp)
    )
}

@Composable
fun HomeCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .offset(y = (-16).dp),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

