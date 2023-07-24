package com.example.hifes

import android.app.Application
import android.content.SharedPreferences
import com.example.hifes.data.local.AppPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var prefs: SharedPreferences
        lateinit var shuttleBusPrefs: SharedPreferences
    }

    override fun onCreate() {
        prefs = AppPreferences.openSharedPreferences(applicationContext)

        super.onCreate()
    }
}