package com.example.hifes.ui.group.info.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hifes.ui.iconpack.MyIconPack
import com.example.hifes.ui.iconpack.myiconpack.Send
import com.example.hifes.ui.theme.PrimaryPink
import com.example.hifes.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInputField(
    onMessageSent: (String) -> Unit
) {
    var message by remember { mutableStateOf("") }

    val borderStroke = BorderStroke(2.dp, Color.LightGray)    // 선택된 보더의 색 변경해야함

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // 채팅 입력 필드
        TextField(
            value = message,
            onValueChange = { newText ->
                message = newText
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(borderStroke, MaterialTheme.shapes.small)
                .weight(1f)
                ,
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
        )

        // 전송 버튼
        IconButton(
            onClick = {
                if (message.isNotBlank()) {
                    onMessageSent(message)
                    message = ""
                }
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = MyIconPack.Send,
                contentDescription = "Send",
                modifier = Modifier.fillMaxSize()
//                tint = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview
@Composable
fun ChatPrev() {
    Column() {
        Spacer(modifier = Modifier.weight(1f))
        ChatInputField(onMessageSent = {})
    }
    
}