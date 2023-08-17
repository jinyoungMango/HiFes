package com.ssafy.hifes.ui.common.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.common.CustomMenuItem
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

// 상단 오른쪽에 올 수 있는 것
// 1. 아무것도 없음
// 2. 점 세개
// 3. 버튼
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopWithBack(
    navController: NavController,
    title: String = "타이틀",
    more: Boolean = false,
    btn: Boolean = false,
    btnText: String = "버튼",
    menuList: MutableList<CustomMenuItem> = mutableListOf(),
    onClick: () -> Unit = {}
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    CenterAlignedTopAppBar(
        navigationIcon = {
            Row(
                modifier = Modifier
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        ImageVector.vectorResource(R.drawable.icon_back),
                        contentDescription = null
                    )
                }
            }
        },

        title = {
            Row(
                modifier = Modifier
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    title,
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

        },
        modifier = Modifier.height(60.dp).background(color = Color.White),
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
                        ) {
                            Text(text = menuItem.title)
                        }
                    }
                }
            }
            if (btn) {
                Button(onClick = onClick, colors = ButtonDefaults.buttonColors(PrimaryPink)) {
                    Text(text = btnText, fontFamily = pretendardFamily, fontWeight = FontWeight.Bold)
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
        TopWithBack(rememberNavController(), title = "타이틀1")
        TopWithBack(rememberNavController(), title = "타이틀2", more = true, onClick = {})
        TopWithBack(
            rememberNavController(),
            title = "타이틀3",
            btn = true,
            btnText = "글쓰기",
            onClick = {})
    }

}