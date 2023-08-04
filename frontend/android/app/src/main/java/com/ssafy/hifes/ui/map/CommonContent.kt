package com.ssafy.hifes.ui.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.theme.pretendardFamily


@Composable
fun MapScreenContent() {
    DialogContent(
        image = R.drawable.ic_launcher_foreground,
        score = 4.0,
        address = "주소",
        startDate = "startDate",
        endDate = "endDate"
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun MapPreview() {
    MapScreenContent()
}

@Composable
fun DialogContent(image: Int, score: Double, address: String, startDate: String, endDate: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Card Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column {
                StarScore(score)
                TextTitleWithContent(address = address, startDate = startDate, endDate = endDate)
            }
        }
    }
}


@Composable
fun StarScore(score: Double) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.icon_star),
            contentDescription = "star",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = score.toString(),
            color = Color.Black,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun TextTitleWithContent(address: String, startDate: String, endDate: String) {
    Spacer(modifier = Modifier.size(4.dp))
    Text(
        text = "장소",
        color = Color.Black,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
    Text(
        text = address,
        color = Color.Black,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    )

    Spacer(modifier = Modifier.size(8.dp))
    Text(
        text = "일정",
        color = Color.Black,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
    Text(
        text = address,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        fontSize = 18.sp
    )
}