package com.ssafy.hifes.ui.group.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.iconpack.myiconpack.Search
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun SearchGroup() {
    var text by remember { mutableStateOf("") }

    val borderStroke = BorderStroke(1.dp, Color.Gray)

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(color = Color.White)) {

        BasicTextField(
            value = text,
            onValueChange = { newText ->
                // 입력 값이 변경되면 상태를 업데이트
                text = newText
            },
            maxLines = 1,
            textStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
            modifier = Modifier
                .border(borderStroke, RoundedCornerShape(32.dp))
                .weight(1f)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = MyIconPack.Search,
                contentDescription = "Serach",
                tint = Color.Red,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(navController: NavController) {
    val image: Painter = painterResource(id = R.drawable.icon_search)
    val otherImage: Painter = painterResource(id = R.drawable.icon_filter)

    CenterAlignedTopAppBar(
        title = { Text(text = "My App") },
        modifier = Modifier.height(60.dp).background(color = Color.White),
        actions = {
            Row(
                modifier = Modifier.background(color = Color.White)
                    .weight(1f)
                    .size(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                var text by remember { mutableStateOf("") }
                Spacer(modifier = Modifier.size(18.dp))
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Normal
                    ),
                    leadingIcon = {
                        IconButton(onClick = { /* do something on click */ }) {
                            Icon(
                                painter = image,
                                contentDescription = "Trailing icon",
                                modifier = Modifier.fillMaxSize(0.7f)
                            )
                        }
                    },
                    placeholder = {
                        Column {
                            Text(
                                stringResource(id = R.string.group_app_bar),
                                fontFamily = pretendardFamily,
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp
                            )
                        }

                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {/* 바텀시트 띄우기 */ },
                            modifier = Modifier
                                .size(34.dp)
                                .border(BorderStroke(0.4.dp, Color.Gray), shape = CircleShape)
                                .padding(2.dp),
                            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White),
                            content = {
                                Icon(
                                    painter = otherImage,
                                    contentDescription = "painter",
                                    tint = Color.DarkGray,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        )

                    },
                    singleLine = true,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(30.dp))
                        .background(color = Color.White)
                        .height(50.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f), // TextField를 Row의 남은 공간에 채우도록 함
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    )
}


@Preview
@Composable
fun SearchPrev() {
//    Column(Modifier.padding(horizontal = 8.dp)) {
//        SearchGroup()
//    }
    SearchAppBar(navController = rememberNavController())
}