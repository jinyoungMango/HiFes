package com.ssafy.hifes.ui.common.top

data class TopAppBarMenuItem(
    var title: String,
    var action: () -> Unit
)