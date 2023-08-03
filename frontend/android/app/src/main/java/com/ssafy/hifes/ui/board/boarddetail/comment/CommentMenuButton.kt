package com.ssafy.hifes.ui.board.boarddetail.comment

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ssafy.hifes.ui.common.CustomMenuItem
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.More

@Composable
fun CommentMenuButton(menuItemList : MutableList<CustomMenuItem>) {
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = MyIconPack.More,
                contentDescription = "More",
                tint = Color.Unspecified
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            menuItemList.forEach {
                DropdownMenuItem(
                    onClick = {
                        it.action()
                        expanded = false
                    }
                ){
                    Text(text = it.title)
                }
            }
        }
    }
}

@Composable
@Preview
fun PreivewCommentMenu(){
    CommentMenuButton(menuItemList = mutableListOf())
}