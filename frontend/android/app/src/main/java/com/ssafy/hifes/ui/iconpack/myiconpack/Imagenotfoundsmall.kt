package com.ssafy.hifes.ui.iconpack.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack

public val MyIconPack.Imagenotfoundsmall: ImageVector
    get() {
        if (_imagenotfoundsmall != null) {
            return _imagenotfoundsmall!!
        }
        _imagenotfoundsmall = Builder(name = "Imagenotfoundsmall", defaultWidth = 400.0.dp,
                defaultHeight = 400.0.dp, viewportWidth = 400.0f, viewportHeight = 400.0f).apply {
            path(fill = SolidColor(Color(0xFFCDCDCD)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(240.667f, 222.958f)
                lineTo(232.333f, 214.625f)
                verticalLineTo(168.167f)
                horizontalLineTo(185.875f)
                lineTo(177.542f, 159.833f)
                horizontalLineTo(232.333f)
                curveTo(234.625f, 159.833f, 236.588f, 160.65f, 238.221f, 162.283f)
                curveTo(239.854f, 163.917f, 240.669f, 165.878f, 240.667f, 168.167f)
                verticalLineTo(222.958f)
                close()
                moveTo(235.667f, 241.5f)
                lineTo(229.0f, 234.833f)
                horizontalLineTo(174.0f)
                curveTo(171.708f, 234.833f, 169.746f, 234.017f, 168.112f, 232.383f)
                curveTo(166.479f, 230.75f, 165.664f, 228.789f, 165.667f, 226.5f)
                verticalLineTo(171.5f)
                lineTo(159.0f, 164.833f)
                lineTo(164.833f, 159.0f)
                lineTo(241.5f, 235.667f)
                lineTo(235.667f, 241.5f)
                close()
                moveTo(178.167f, 218.167f)
                lineTo(190.667f, 201.5f)
                lineTo(200.042f, 214.0f)
                lineTo(203.479f, 209.417f)
                lineTo(174.0f, 179.938f)
                verticalLineTo(226.5f)
                horizontalLineTo(220.563f)
                lineTo(212.229f, 218.167f)
                horizontalLineTo(178.167f)
                close()
            }
        }
        .build()
        return _imagenotfoundsmall!!
    }

private var _imagenotfoundsmall: ImageVector? = null
