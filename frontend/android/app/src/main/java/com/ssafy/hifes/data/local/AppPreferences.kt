package com.ssafy.hifes.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object AppPreferences {
    private lateinit var preferences: SharedPreferences
    private val gson = GsonBuilder().create()

    private const val LOGIN_SESSION = "login.session"
    private const val USER_ID = "user_id"

    fun openSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
    }

    fun getUserId(): Int {
        return preferences.getInt(USER_ID, -1)
    }

    fun saveUserId(userId: Int) {
        //preferences.edit().putInt(USER_ID, userId).apply()
        preferences.edit().putInt(USER_ID, 300).apply() //로그인 기능 완료 후 수정 필요
    }

}