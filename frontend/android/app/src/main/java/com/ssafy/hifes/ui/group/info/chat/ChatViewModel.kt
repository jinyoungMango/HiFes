package com.ssafy.hifes.ui.group.info.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.MessageData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val TAG = "ChatViewModel_하이페스"

class ChatViewModel : ViewModel() {
    private val firebaseDB = Firebase.database

    var chatMessages = MutableStateFlow<List<MessageData>>(emptyList())
//    var chatData = MutableStateFlow<ChatData?>(null)
    var userId = 29

    // 채팅방 들어가자마자 조회 - 한번도 채팅하지 않은 경우(채팅방이 생성되어있지 않은 경우) 조회불가
    fun enterChatRoom(chatId: String) {
        // 한번도 채팅하지 않은경우는 조회 불가
        val userId = AppPreferences.getUserId()
        Log.d("채팅방 id", chatId)
        firebaseDB.reference.child("chat").child(chatId).get()
            .addOnSuccessListener {
                Log.d("채팅방 정보", it.value.toString())
                // 데이터는 hashMap 형태로 오기때문에 객체 형태로 변환해줘야함
//                it.value?.let { value ->
//                    val result = value as HashMap<String, Any>?
//                    val writer = result?.get("writer") as HashMap<String, Any>?
//                    val contact = result?.get("contact") as HashMap<String, Any>?
//                    val _chatData = ChatData(
//                        id = userId,
//
//                    )
//                    chatData.value = _chatData
//                }
            }
            .addOnFailureListener {
                Log.d("채팅룸 정보 가져오기 실패", it.toString())
            }

        val chatListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val _chatMessage = arrayListOf<MessageData>()
                val messageData = snapshot.value as ArrayList<HashMap<String, Any>>?

                // snapshot은 hashMap 형태로 오기때문에 객체 형태로 변환해줘야함
                messageData?.forEach {
//                    _chatMessage.add(
//                        MessageData(
//                            it["content"] as String,
//                            it["createdAt"] as Long,
//                            (it["id"] as Long).toInt()
//                        )
//                    )
                }
                chatMessages.value = arrayListOf()
                chatMessages.value = _chatMessage.toList()
                Log.d("변화 리스너2", chatMessages.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "loadMessage:onCancelled", error.toException())
            }
        }
        firebaseDB.reference.child("chat").child(chatId).child("messages")
            .addValueEventListener(chatListener)
        Firebase
    }

    // 메세지 보내기
    fun newMessage(chatId: String, messageData: MessageData) {
        if (chatMessages.value.isEmpty()) {
            chatMessages.value = listOf(messageData)
            // 첫 메세지일때 채팅방 생성 - 채팅룸이 생성되는 시점
//            newChatRoom(chatId, userId, chatMessages.value)
        } else {
            chatMessages.value += messageData
            firebaseDB.reference.child("chat").child(chatId).child("messages")
                .setValue(chatMessages.value)
                .addOnSuccessListener {
                    Log.d("newChatRoomSuccess", "메세지 보내기 성공")
                }
                .addOnFailureListener {
                    Log.d("메세지 보내기 실패", it.toString())
                }
        }
    }

    fun testNewMessage(groupId: String, messageData: MessageData) {

            Log.d(TAG, "testNewMessage: chatMeesages -> ${chatMessages.value}")
            chatMessages.value += messageData
            Log.d(TAG, "testNewMessage: chatMeesages -> ${chatMessages.value}")

//            firebaseDB.reference.child("chat").child(groupId).child("messages").push()
//                .setValue(chatMessages.value)
//                .addOnSuccessListener {
//                    Log.d("newChatRoomSuccess", "메세지 보내기 성공")
//                }
//                .addOnFailureListener {
//                    Log.d("메세지 보내기 실패", it.toString())
//                }

            firebaseDB.reference.child("chat").child(groupId).child("messages").push()
                .setValue(chatMessages.value)
                .addOnSuccessListener {
                    Log.d("newChatRoomSuccess", "메세지 보내기 성공")
                }
                .addOnFailureListener {
                    Log.d("메세지 보내기 실패", it.toString())
                }

    }

    // 채팅룸 생성
    private fun newChatRoom(
        groupId: String,
        message: List<MessageData>
    ) {
        viewModelScope.launch {
//            val chatData = ChatData(chatId, message)

            firebaseDB.reference.child("chat").child(groupId).setValue(message)
                .addOnSuccessListener {
                    Log.d("newChatRoomSuccess", "채팅룸 생성 완료")
                    // 생성시 채팅 listener 재호출
                    enterChatRoom(groupId)
                }
                .addOnFailureListener {
                    Log.d("채팅룸 생성 실패", it.toString())
                }


        }
    }
}