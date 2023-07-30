package com.example.hifes.ui.iconpack

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hifes.ui.iconpack.myiconpack.Camera
import com.example.hifes.ui.iconpack.myiconpack.Leave
import com.example.hifes.ui.iconpack.myiconpack.Search
import com.example.hifes.ui.iconpack.myiconpack.Send
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Camera, Leave, Search, Send)
    return __AllIcons!!
  }
