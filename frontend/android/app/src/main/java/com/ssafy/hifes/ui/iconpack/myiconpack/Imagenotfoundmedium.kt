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

public val MyIconPack.Imagenotfoundmedium: ImageVector
    get() {
        if (_imagenotfoundmedium != null) {
            return _imagenotfoundmedium!!
        }
        _imagenotfoundmedium = Builder(name = "Imagenotfoundmedium", defaultWidth = 200.0.dp,
                defaultHeight = 200.0.dp, viewportWidth = 200.0f, viewportHeight = 200.0f).apply {
            path(fill = SolidColor(Color(0xFFCDCDCD)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(140.667f, 123.958f)
                lineTo(132.333f, 115.625f)
                verticalLineTo(69.167f)
                horizontalLineTo(85.875f)
                lineTo(77.542f, 60.833f)
                horizontalLineTo(132.333f)
                curveTo(134.625f, 60.833f, 136.588f, 61.65f, 138.221f, 63.283f)
                curveTo(139.854f, 64.917f, 140.669f, 66.878f, 140.667f, 69.167f)
                verticalLineTo(123.958f)
                close()
                moveTo(135.667f, 142.5f)
                lineTo(129.0f, 135.833f)
                horizontalLineTo(74.0f)
                curveTo(71.708f, 135.833f, 69.746f, 135.017f, 68.113f, 133.383f)
                curveTo(66.479f, 131.75f, 65.664f, 129.789f, 65.667f, 127.5f)
                verticalLineTo(72.5f)
                lineTo(59.0f, 65.833f)
                lineTo(64.833f, 60.0f)
                lineTo(141.5f, 136.667f)
                lineTo(135.667f, 142.5f)
                close()
                moveTo(78.167f, 119.167f)
                lineTo(90.667f, 102.5f)
                lineTo(100.042f, 115.0f)
                lineTo(103.479f, 110.417f)
                lineTo(74.0f, 80.938f)
                verticalLineTo(127.5f)
                horizontalLineTo(120.563f)
                lineTo(112.229f, 119.167f)
                horizontalLineTo(78.167f)
                close()
            }
        }
        .build()
        return _imagenotfoundmedium!!
    }

private var _imagenotfoundmedium: ImageVector? = null
