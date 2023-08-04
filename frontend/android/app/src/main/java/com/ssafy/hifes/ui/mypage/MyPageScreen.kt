package com.ssafy.hifes.ui.mypage

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ssafy.hifes.R

import com.ssafy.hifes.ui.common.top.TopWithBack
import com.ssafy.hifes.ui.iconpack.MyIconPack
import com.ssafy.hifes.ui.mypage.TitleText
import com.ssafy.hifes.ui.theme.Grey
import myiconpack.User

private const val TAG = "MyPageScreen"
@Composable
fun MyPageScreen(
    navController: NavController
) {
    val accountSubTitles = mutableListOf<String>().apply {
        add(stringResource(id = R.string.mypage_change_nickname))
        add(stringResource(id = R.string.mypage_change_profile_image))
        add(stringResource(id = R.string.mypage_logout))
        add(stringResource(id = R.string.mypage_withdraw))
    }
    val infoSubTitles = mutableListOf<String>().apply {
        add(stringResource(id = R.string.mypage_terms))
        add(stringResource(id = R.string.mypage_privacy_policy))
        add(stringResource(id = R.string.mypage_used_library))
    }
    val eventSubTitles = mutableListOf<String>().apply {
        add(stringResource(id = R.string.mypage_participated_event))
    }
    val context = LocalContext.current
    val testFun : () -> Unit = {
        Toast.makeText(context, "hihi", Toast.LENGTH_LONG).show()
        Log.d(TAG, "MyPageScreen: 1111111111111111111111111")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        TopWithBack(navController, title = stringResource(R.string.mypage_appbar_title))
        Spacer(modifier = Modifier.size(20.dp))

        Column(modifier = Modifier.padding(20.dp, 0.dp)) {
            Row(
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = Grey,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(18.dp, 10.dp, 18.dp, 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
                    contentDescription = "프로필 이미지",
                    placeholder = rememberVectorPainter(image = MyIconPack.User),
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.size(20.dp))
                TitleText(title = "Mango")
            }
            Spacer(modifier = Modifier.size(20.dp))

            GreyBorderItem(
                title = stringResource(id = R.string.mypage_account),
                subTitleList = accountSubTitles,
                onClick = testFun
            )
            Spacer(modifier = Modifier.size(20.dp))
            GreyBorderItem(
                title = stringResource(id = R.string.mypage_info),
                subTitleList = infoSubTitles,
                onClick = testFun
            )
            Spacer(modifier = Modifier.size(20.dp))
            GreyBorderItem(
                title = stringResource(id = R.string.mypage_event),
                subTitleList = eventSubTitles,
                onClick = testFun
            )
        }
    }
}

@Preview
@Composable
fun MyPageScreenPrev() {
    MyPageScreen(navController = rememberNavController())
}