package com.ssafy.hifes.ui.map

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ssafy.hifes.ui.detail.DetailViewModel
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun MapGroupCallDialog(
    viewModel: DetailViewModel,
    lat: Double,
    lng: Double,
    onDismiss: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var message by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = { onDismiss(false) }) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            ) {

                Text(
                    text = "모임콜 메세지 입력",
                    modifier = Modifier.padding(8.dp),
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )

                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it }, modifier = Modifier.padding(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = PrimaryPink
                    ),
                    textStyle = TextStyle(fontFamily = pretendardFamily)
                )

                Row {
                    OutlinedButton(
                        onClick = { onDismiss(false) },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(
                            text = "취소",
                            color = PrimaryPink,
                            fontFamily = pretendardFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }


                    Button(
                        onClick = {
//                            viewModel.callGroupNotification()
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            onDismiss(true)
                        },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryPink)
                    ) {
                        Text(
                            text = "확인",
                            color = Color.White,
                            fontFamily = pretendardFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }


            }
        }
    }
}