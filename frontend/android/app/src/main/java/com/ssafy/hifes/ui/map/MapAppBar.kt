package com.ssafy.hifes.ui.map

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapAppBar(navController: NavController) {
    val image: Painter = painterResource(id = R.drawable.icon_search)
    val otherImage: Painter = painterResource(id = R.drawable.icon_back)

    TopAppBar(
        title = { Text(text = "My App") },
        colors = topAppBarColors(
            containerColor = Color.White.copy(alpha = 0.0f)
        ),
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) { // Row 레이아웃 사용
                var text by remember { mutableStateOf("") }
                IconButton(onClick = { navController.popBackStack()}) {
                    Icon(
                        painter = otherImage,
                        contentDescription = "Other icon",
                        modifier = Modifier.fillMaxSize(0.9f)
                    )
                }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    maxLines = 1,
                    leadingIcon = {
                        IconButton(onClick = { /* do something on click */ }) {
                            Icon(
                                painter = image,
                                contentDescription = "Trailing icon",
                                modifier = Modifier
                                    .fillMaxSize(1f)
                                    .padding(2.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(20.dp))
                        .weight(0.9f)
                        .height(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                    )
                )

            }
        }
    )
}

@Preview
@Composable
fun MapAppBarPreview() {
    MapAppBar(rememberNavController())
}
