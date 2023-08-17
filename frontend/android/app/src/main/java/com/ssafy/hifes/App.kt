package com.ssafy.hifes

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.ssafy.hifes.data.AppContainer
import com.ssafy.hifes.data.AppContainerImpl
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.service.FirebaseMessageService
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "App_하이패스앱"
@HiltAndroidApp
class App : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        AppPreferences.openSharedPreferences(applicationContext)
        FirebaseMessageService().getFirebaseToken()
        container = AppContainerImpl(this)
        // Kakao SDK 초기화
        KakaoSdk.init(this, BuildConfig.API_KEY)
        val data = AppPreferences.getCallLocation()
        Log.d(TAG, "onCreate: ${data.first} ${data.second[0]} ${data.second[1]}")
        super.onCreate()
    }
}