package com.example.hifes.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hifes.App
import com.example.hifes.ui.HifesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as App).container
        setContent {
            HifesApp(appContainer = appContainer)
        }
    }
}