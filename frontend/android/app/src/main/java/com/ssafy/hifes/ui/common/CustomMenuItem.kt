package com.ssafy.hifes.ui.common

data class MenuItem(
    var title: String,
    var action: () -> Unit
)