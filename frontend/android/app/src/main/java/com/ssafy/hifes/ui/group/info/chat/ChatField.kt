package com.ssafy.hifes.ui.group.info.chat

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.MessageData
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily


@Composable
fun ChatInputField(
    onMessageSent: (String, Long) -> Unit
) {
    var message by remember { mutableStateOf("") }
    val timestamp = remember {
        mutableStateOf<Long>(0)
    }
    val borderStroke = BorderStroke(2.dp, Color.LightGray)    // 선택된 보더의 색 변경해야함

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        // 채팅 입력 필드
        TextField(
            value = message,
            onValueChange = { message = it },
            trailingIcon = {  // Instead of directly passing the IconButton, it should be wrapped in a trailingIcon lambda.
                IconButton(
                    onClick = {
                        if (message.isNotEmpty()) {
                            Log.d("새로운 메세지", message)
                            timestamp.value = System.currentTimeMillis()
                            onMessageSent(message, timestamp.value)
                            message = ""
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "보내기",
                    )
                }
            },
            placeholder = {
                Text(
                    stringResource(id = R.string.group_chat),
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(borderStroke, MaterialTheme.shapes.small)
                .weight(1f),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 18.sp,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal
            ),
        )

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ChatPrev() {
    Column() {
        Spacer(modifier = Modifier.weight(1f))
        ChatInputField(onMessageSent = {String, Long -> })
    }

}