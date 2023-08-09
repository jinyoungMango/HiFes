package com.ssafy.hifes.ui.login

import NavigationItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.theme.Grey
import com.ssafy.hifes.ui.theme.KakaoYellow
import com.ssafy.hifes.ui.theme.NaverGreen
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

@Composable
fun LoginScreen(navController: NavController) {
    var isSplashFinished by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isSplashFinished) {
            SplashScreen(onFinished = { isSplashFinished = true })
        } else {
            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier.weight(3f).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                LogoAndTitle()
                Buttons(navController, Modifier.padding(40.dp, 20.dp))
            }
        }
    }
}

@Composable
fun LogoAndTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.logo_hifes),
            contentDescription = "logo",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = stringResource(id = R.string.login_sub_title),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Bold,
            color = Grey,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(id = R.string.login_title),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Black,
            color = PrimaryPink,
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
            color = KakaoYellow,
            title = stringResource(R.string.kakao_login),
            onClick = {
                navController.navigate(NavigationItem.Home.screenRoute) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            },
            textColor = R.color.black
        )

    }
}


@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen(navController = rememberNavController())
}