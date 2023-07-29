package com.example.hifes.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hifes.R
import com.example.hifes.ui.HifesDestinations

@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LoginButton(
            color = colorResource(R.color.kakao_yellow),
            title = stringResource(R.string.kakao_login),
            onClick = { context ->
                navController.navigate(HifesDestinations.LOGIN_DETAIL_ROUTE)
            }
        )
        LoginButton(
            color = colorResource(R.color.naver_green),
            title = stringResource(R.string.naver_login),
            onClick = { context ->
                navController.navigate(HifesDestinations.LOGIN_DETAIL_ROUTE)
            }
        )
    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen(navController = rememberNavController())
}