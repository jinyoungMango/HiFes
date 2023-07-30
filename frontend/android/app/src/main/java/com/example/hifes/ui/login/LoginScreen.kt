package com.example.hifes.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoAndTitle()
        Buttons(navController, Modifier.padding(40.dp, 20.dp))

    }
}

@Composable
fun LogoAndTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.logo_hifes),
            contentDescription = "logo",
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = stringResource(id = R.string.login_sub_title),
            fontWeight = FontWeight.Black,
            color = colorResource(
                id = R.color.grey
            ),
            fontSize = 14.sp
        )
        Text(
            text = stringResource(id = R.string.login_title),
            fontWeight = FontWeight.Black,
            color = colorResource(
                id = R.color.main_pink
            ),
            fontSize = 32.sp
        )

    }
}

@Composable
fun Buttons(navController: NavController, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        LoginButton(
            color = colorResource(R.color.kakao_yellow),
            title = stringResource(R.string.kakao_login),
            onClick = { context ->
                navController.navigate(HifesDestinations.LOGIN_DETAIL_ROUTE)
            },
            textColor = R.color.black
        )
        Spacer(modifier = Modifier.size(10.dp))
        LoginButton(
            color = colorResource(R.color.naver_green),
            title = stringResource(R.string.naver_login),
            onClick = { context ->
                navController.navigate(HifesDestinations.LOGIN_DETAIL_ROUTE)
            },
            textColor = R.color.white
        )
        LoginMaintain()
    }
}

@Composable
fun LoginMaintain() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(selected = false, onClick = { }, modifier = Modifier.padding(0.dp))
        Text(text = stringResource(id = R.string.login_maintain), fontSize = 10.sp)
    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen(navController = rememberNavController())
}