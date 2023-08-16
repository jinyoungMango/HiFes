package com.ssafy.hifes.ui.group.info.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.group.GroupViewModel

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
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(20.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(24.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(28.dp)
            ) {
                var dialogText = stringResource(id = R.string.group_dialog_join)
                if (isJoin) {
                    dialogText = stringResource(id = R.string.group_dialog_sign_out)
                }

                Text(
                    text = dialogText,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )

                Row {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = stringResource(id = R.string.group_dialog_cancel))
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
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = stringResource(id = R.string.group_dialog_confirm))
                    }
                }


            }
        }
    }
}
