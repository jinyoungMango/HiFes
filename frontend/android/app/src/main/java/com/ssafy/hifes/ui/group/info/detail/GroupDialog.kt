package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun GroupDialog(
    navController: NavController,
    groupViewModel: GroupViewModel,
    groupId: Int,
    isJoin: Boolean,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp).background(color = Color.White)
            ) {
                var dialogText = stringResource(id = R.string.group_dialog_join)
                if (isJoin) {
                    dialogText = stringResource(id = R.string.group_dialog_sign_out)
                }

                Text(
                    text = dialogText,
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )

                Row {
                    OutlinedButton(
                        onClick = { onDismiss() },
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
                            if (!isJoin) {
                                groupViewModel.joinGroup(groupId)
                            } else {
                                groupViewModel.signOutGroup(groupId)
                            }
                            navController.navigate(HifesDestinations.GROUP_DETAIL) {
                                popUpTo(HifesDestinations.GROUP_DETAIL) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                            onDismiss()
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
