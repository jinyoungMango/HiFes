package com.ssafy.hifes.ui.group.info.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.MessageData
import com.ssafy.hifes.data.model.UserData
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.theme.pretendardFamily
import com.ssafy.hifes.util.CommonUtils

@Composable
fun GroupChatScreen(viewModel: ChatViewModel, groupViewModel: GroupViewModel) {
    val groupId = groupViewModel.selectedGroup.observeAsState()
    val userId = AppPreferences.getUserId()
    val nickname = AppPreferences.getUserNickname()
    val userData = UserData(nickname, userId)
    val chatMessages = viewModel.chatMessages.collectAsState()
    val listState = rememberLazyListState()
    var triggerScroll by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // 채팅창 들어가서 정보 가져오기- 채팅 데이터
        viewModel.enterChatRoom(groupId.value.toString())
    }

    LaunchedEffect(triggerScroll) {
        if (triggerScroll) {
            listState.animateScrollToItem(index = listState.layoutInfo.totalItemsCount - 1)
            triggerScroll = false
        }
    }

    Column {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.Bottom
            ) {
                items(chatMessages.value) { message ->
                    ChatBubble(
                        message = message,
                        userId = userId,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
        ChatInputField(onMessageSent = { message, time ->
            var messageData = MessageData(message, userData, time)
            viewModel.sendNewMessage(groupId = groupId.value.toString(), messageData = messageData)
            triggerScroll = true

        })
    }
}

@Composable
fun ChatBubble(
    message: MessageData,
    userId: String?,
    modifier: Modifier = Modifier
) {

    val isMe = message.userData.id == userId
    val bubbleColor = if (isMe) {
        Color(0xFFFFE5AD)
    } else {
        Color(0xFFE8E8E8)
    }

    val bubbleShape = if (isMe) {
        RoundedCornerShape(16.dp, 0.dp, 16.dp, 16.dp)
    } else {
        RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp)
    }

    val bubbleArrange = if (isMe) {
        Alignment.End
    } else {
        Alignment.Start
    }

    val textColor = Color.Black
    val padding = 8.dp
    Row {
        if (!isMe) {
            AsyncImage(
                model = "https://mblogthumb-phinf.pstatic.net/MjAxODAzMDNfMTc5/MDAxNTIwMDQxNzQwODYx.qQDg_PbRHclce0n3s-2DRePFQggeU6_0bEnxV8OY1yQg.4EZpKfKEOyW_PXOVvy7wloTrIUzb71HP8N2y-YFsBJcg.PNG.osy2201/1_%2835%ED%8D%BC%EC%84%BC%ED%8A%B8_%ED%9A%8C%EC%83%89%29_%ED%9A%8C%EC%83%89_%EB%8B%A8%EC%83%89_%EB%B0%B0%EA%B2%BD%ED%99%94%EB%A9%B4_180303.png?type=w800",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.Gray),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = bubbleArrange) {
            if (!isMe) {
                Text(text = message.userData.nickname!!, fontFamily = pretendardFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Box(
            ) {
                Row(
                    modifier = Modifier
                        .background(color = bubbleColor, shape = bubbleShape)
                        .padding(padding)
                ) {
                    Text(
                        text = message.content,
                        color = textColor,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically).padding(2.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = CommonUtils.formatLongToTime(message.time),
                modifier = Modifier.padding(start = 4.dp),
                color = Color.Gray,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

        }
    }

}

@Preview
@Composable
fun GroupChatScreenPrev() {
//    GroupChatScreen(viewModel = ChatViewModel())
}