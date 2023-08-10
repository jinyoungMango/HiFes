package com.ssafy.hifes.ui.login

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.theme.Grey
import com.ssafy.hifes.ui.theme.KakaoYellow
import com.ssafy.hifes.ui.theme.PrimaryPink
import com.ssafy.hifes.ui.theme.pretendardFamily

private const val TAG = "LoginScreen_하이페스"

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
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        LoginButton(
            color = KakaoYellow,
            title = stringResource(R.string.kakao_login),
            onClick = {
                login2(navController, context)

            },
            textColor = R.color.black
        )

    }
}

private fun login2(navController: NavController, context: Context) {
    // 카카오계정으로 로그인 공통 callback 구성
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("카카오계정으로 로그인 실패", error.toString())
        } else if (token != null) {
            Log.i("카카오계정으로 로그인 성공 ${token.accessToken}", token.accessToken)
            // jwt 토큰 발급 & sharedPreferences에 jwt 토큰 저장
            navController.navigate(NavigationItem.Home.screenRoute) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        }
    }
    // 카카오계정으로 로그인
    UserApiClient.instance.loginWithKakaoAccount(
        context,
        callback = callback
    )

}

@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen(navController = rememberNavController())
}