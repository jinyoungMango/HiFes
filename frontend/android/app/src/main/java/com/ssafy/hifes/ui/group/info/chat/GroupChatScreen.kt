package com.ssafy.hifes.ui.group.info.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// 채팅아이템용 DTO도 만들어야 함
data class ChatItem (
    var text: String,
    var isSentByUser: Boolean,
)

val item = ChatItem("텍스트", true)
var itemList = listOf(item, item, item, item, item, item, item, item,)
@Composable
fun GroupChatScreen() {
    Column {
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            LazyColumn() {
                items(itemList) { item ->
                    ChatBubble(chatItem = item, modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        }
        ChatInputField(onMessageSent = {})
    }
}

@Preview
@Composable
fun GroupChatScreenPrev() {
    GroupChatScreen()
}