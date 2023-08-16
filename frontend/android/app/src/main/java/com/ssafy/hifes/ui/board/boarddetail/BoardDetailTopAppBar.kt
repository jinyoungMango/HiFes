package com.ssafy.hifes.ui.board.boarddetail

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ssafy.hifes.R
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.PostDetailDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.common.CustomMenuItem
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.LightGrey

@Composable
fun BoardDetailTopAppBar(
    navController: NavController,
    postData: PostDetailDto,
    viewModel: BoardViewModel
) {
    var userId = AppPreferences.getUserId()
    var appBarTitle = getBoardDetailAppBarTitle(postData.postType, LocalContext.current)
    var menuList: MutableList<CustomMenuItem> = mutableListOf()

    if (postData.createdBy.toString() == userId) {
        menuList.apply {
            add(
                CustomMenuItem(
                    stringResource(id = R.string.board_detail_modify),
                    { viewModel.postModify() })
            )
            add(
                CustomMenuItem(
                    stringResource(id = R.string.board_detail_delete),
                    { viewModel.postDelete() })
            )
        }
    }

    Column {
        if (postData.postType == PostType.NOTIFICATION.label || postData.createdBy.toString() != userId) {
            TopWithBack(navController, title = appBarTitle)
        } else {
            TopWithBack(navController, title = appBarTitle, more = true, menuList = menuList)
        }
    }
    Divider(color = LightGrey, thickness = 2.dp)
}

fun getBoardDetailAppBarTitle(boardType: String, context: Context): String {
    var appBarTitle = ""
    when (boardType) {
        PostType.NOTIFICATION.label -> appBarTitle =
            context.getString(R.string.board_chip_notification)

        PostType.ASK.label -> appBarTitle = context.getString(R.string.board_chip_ask)
        PostType.FREE.label -> appBarTitle = context.getString(R.string.board_chip_free)
        PostType.REVIEW.label -> appBarTitle = context.getString(R.string.board_chip_review)
    }
    return appBarTitle
}