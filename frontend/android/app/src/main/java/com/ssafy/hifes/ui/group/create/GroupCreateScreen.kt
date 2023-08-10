package com.ssafy.hifes.ui.group.create

import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.ui.common.ProfileImg
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupCreateScreen(navController: NavController) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Scaffold (
        topBar = { TopWithBack(navController, title = "모임 생성", onClick = {})},
        content = {
            val scrollState = rememberScrollState()

            Column(
                Modifier
                    .padding(it)
                    .padding(start = 18.dp, end = 18.dp)
                    .verticalScroll(scrollState)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally) {
                ProfileImg(imageUri = imageUri, onImageChange = { uri -> imageUri = uri })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithCaption(caption = "모임명")

                DropdownWithCaption(caption = "최대 인원")
                TextFieldWithCaption(caption = "태그 작성")

                Spacer(modifier = Modifier.height(24.dp))
                Row (modifier = Modifier.fillMaxWidth(),  horizontalArrangement = Arrangement.SpaceBetween){
                    Button(modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.medium,border = BorderStroke(1.dp, PrimaryPink), onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black )) {
                        Text(text = "취소", fontFamily = pretendardFamily, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(24.dp))
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
    GroupCreateScreen(rememberNavController())
}