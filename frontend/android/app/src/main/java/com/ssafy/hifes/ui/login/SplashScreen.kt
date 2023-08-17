package com.ssafy.hifes.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ssafy.hifes.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation1))
    val progress by animateLottieCompositionAsState(
        composition,
        speed = 2.5f,
        iterations = 2,
        clipSpec = LottieClipSpec.Progress(0.0f, 0.95f),
    )
    Surface(modifier = Modifier.padding(72.dp)) {
        LottieAnimation(composition = composition, progress = progress)
    }

    if (progress >= 0.95f) {
        onFinished()
    }
}