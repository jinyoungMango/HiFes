package com.ssafy.hifes.ui.common

data class CustomMenuItem(
    var title: String,
    var action: () -> Unit
)