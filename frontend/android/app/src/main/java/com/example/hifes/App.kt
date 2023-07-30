package com.example.hifes

import android.app.Application
import android.content.SharedPreferences
import com.example.hifes.data.AppContainer
import com.example.hifes.data.AppContainerImpl
import com.example.hifes.data.local.AppPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var prefs: SharedPreferences
        lateinit var shuttleBusPrefs: SharedPreferences
    }

    lateinit var container: AppContainer

    override fun onCreate() {
        prefs = AppPreferences.openSharedPreferences(applicationContext)
        container = AppContainerImpl(this)
        super.onCreate()
    }
}