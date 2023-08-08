package com.ssafy.hifes.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ssafy.hifes.R


@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation1))
    val progress by animateLottieCompositionAsState(
        composition,
        speed = 2.5f,
        iterations = 2,
        clipSpec = LottieClipSpec.Progress(0.0f, 0.95f),
    )
    LottieAnimation(composition = composition, progress = progress)

    if (progress >= 0.95f) {
        onFinished()
    }
}


@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreenPreview()
}