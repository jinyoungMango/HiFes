package com.ssafy.hifes.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object AppPreferences {
    private lateinit var preferences: SharedPreferences
    private val gson = GsonBuilder().create()

    private const val LOGIN_SESSION = "login.session"

    fun openSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
    }

}