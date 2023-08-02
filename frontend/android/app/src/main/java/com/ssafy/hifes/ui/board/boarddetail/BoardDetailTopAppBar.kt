package com.ssafy.hifes.ui.board.boarddetail

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.PostDto
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boardcommon.PostType
import com.ssafy.hifes.ui.common.top.TopAppBarMenuItem
import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.theme.LightGrey

@Composable
fun BoardDetailTopAppBar(postData: PostDto, viewModel: BoardViewModel) {
    var appBarTitle = getBoardDetailAppBarTitle(postData.postType, LocalContext.current)
    var menuList: MutableList<TopAppBarMenuItem> = mutableListOf()

    if (postData.normalUserId == viewModel.userDataId) {
        menuList.apply {
            add(
                TopAppBarMenuItem(
                    stringResource(id = R.string.board_detail_modify),
                    { viewModel.postModify() })
            )
            add(
                TopAppBarMenuItem(
                    stringResource(id = R.string.board_detail_delete),
                    { viewModel.postDelete() })
            )
        }
    }

    Column {
        if (postData.postType == PostType.NOTIFICATION.label || postData.normalUserId != viewModel.userDataId) {
            TopWithBack(title = appBarTitle)
        } else {
            TopWithBack(title = appBarTitle, more = true, menuList = menuList)
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