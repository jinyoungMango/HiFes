package com.ssafy.hifes.ui.group.create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.group.create.Toggle
import com.ssafy.hifes.ui.common.ProfileImg
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.PrimaryPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupCreateScreen() {
    Scaffold (
        topBar = { TopWithBack(title = "모임 생성", onClick = {})},
        content = {
            val scrollState = rememberScrollState()

            Column(
                Modifier
                    .padding(it)
                    .verticalScroll(scrollState)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally) {
                ProfileImg()
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithCaption(caption = "모임명")
                TextFieldWithCaption(caption = "비밀번호")
                DropdownWithCaption(caption = "최대 인원")
                TextFieldWithCaption(caption = "태그 작성")
                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                    Text("모임 공개", fontWeight = FontWeight.Bold)
                    Toggle()
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row (modifier = Modifier.fillMaxWidth(),  horizontalArrangement = Arrangement.SpaceBetween){
                    Button(modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.medium,border = BorderStroke(1.dp, PrimaryPink), onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black )) {
                        Text(text = "취소")
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.medium,onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryPink, )) {
                        Text(text = "생성")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    )
}

@Preview
@Composable
fun CreatePrev() {
    GroupCreateScreen()
}