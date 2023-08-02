package com.ssafy.hifes.ui.common.top

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Imagenotfound
import kotlin.math.exp

// 상단 오른쪽에 올 수 있는 것
// 1. 아무것도 없음
// 2. 점 세개
// 3. 버튼
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopWithBack(
    title: String = "타이틀",
    more: Boolean = false,
    btn: Boolean = false,
    btnText: String = "버튼",
    menuList: MutableList<TopAppBarMenuItem> = mutableListOf(),
    onClick: () -> Unit = {}
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* 뒤로가기 */ }) {
                Icon(
                    ImageVector.vectorResource(R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null
                )
            }
        },

        title = {
            Text(title)
        },

        actions = {
            if (more) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_more_vert_24),
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    menuList.forEach { menuItem ->
                        DropdownMenuItem(
                            onClick = {
                                menuItem.action()
                                expanded = false
                            }
                        ){
                            Text(text = menuItem.title)
                        }
                    }
                }
            }
            if (btn) {
                Button(onClick = onClick) {
                    Text(text = btnText)
                }
            }
        },
        scrollBehavior = null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopPrev() {
    Column(Modifier.padding(vertical = 8.dp)) {
        TopWithBack(title = "타이틀1")
        TopWithBack(title = "타이틀2", more = true, onClick = {})
        TopWithBack(title = "타이틀3", btn = true, btnText = "글쓰기", onClick = {})
    }

}