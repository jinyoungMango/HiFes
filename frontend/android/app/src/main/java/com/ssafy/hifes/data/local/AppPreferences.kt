package com.ssafy.hifes.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.ssafy.hifes.data.model.LoginResponse

object AppPreferences {
    private lateinit var preferences: SharedPreferences
    private val gson = GsonBuilder().create()

    private const val LOGIN_SESSION = "login.session"

    private const val ACCESS_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"
    private const val USER_ID = "user_id"
    private const val USER_NICKNAME = "user_nickname"


    fun openSharedPreferences(context: Context) {
        preferences = context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
    }

    fun initLoginInfo(loginResponse: LoginResponse) {
        preferences.edit().putString(ACCESS_TOKEN, loginResponse.accessToken)
            .putString(REFRESH_TOKEN, loginResponse.refreshToken)
            .putString(USER_ID, loginResponse.id)
//            .putString(USER_NICKNAME, loginResponse.)
            .commit()
    }

    // sharedPreferences에 저장된 정보 반환
    fun getAccessToken(): String? {
        return preferences.getString(ACCESS_TOKEN, "")
    }

    fun getRefreshToken(): String? {
        return preferences.getString(REFRESH_TOKEN, "")
    }

    fun getUserId(): String? {
        return preferences.getString(USER_ID, "")
    }

    fun getUserNickname(): String? {
        return preferences.getString(USER_ID, "")
    }

}