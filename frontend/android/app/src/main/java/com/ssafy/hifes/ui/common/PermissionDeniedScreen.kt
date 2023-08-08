package com.ssafy.hifes.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun PermissionDeniedScreen() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()){
        Text(text = "권한 설정을 거부하면 어플리케이션 사용이 불가합니다.", textAlign = TextAlign.Center, fontFamily = pretendardFamily,)
        Text(text = "권한 설정을 해 주세요.", textAlign = TextAlign.Center, fontFamily = pretendardFamily,)
        Text(text = "설정->애플리케이션->\"하이페스\"검색->권한->모든 권한 허용하기", textAlign = TextAlign.Center, fontFamily = pretendardFamily,)
    }
}