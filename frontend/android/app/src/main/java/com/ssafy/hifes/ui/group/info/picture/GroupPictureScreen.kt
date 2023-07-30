package com.ssafy.hifes.ui.group.info.picture

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ssafy.hifes.ui.group.info.detail.GroupPictureGrid
import com.ssafy.hifes.ui.group.info.detail.Img

@Composable
fun GroupPictureScreen() {
    val img = Img(
        url = "https://fastly.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U"
    )

    GroupPictureGrid(img = listOf(img, img, img, img, img, img, img, img, img, img, img, img))
}

@Preview
@Composable
fun GroupPictureScreenPrev() {
    GroupPictureScreen()
}