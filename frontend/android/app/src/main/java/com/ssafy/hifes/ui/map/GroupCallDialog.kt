package com.ssafy.hifes.ui.map

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun GroupCallDialog(
    onDismiss: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss(false) }) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {


                Text(
                    text = "모임콜 위치에 도착하셨습니까?",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
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
                            text = stringResource(id = R.string.group_dialog_cancel),
                            color = PrimaryPink,
                            fontFamily = pretendardFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Button(
                        onClick = {
                            onDismiss(true)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryPink),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(
                            text = stringResource(id = R.string.group_dialog_confirm),
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
