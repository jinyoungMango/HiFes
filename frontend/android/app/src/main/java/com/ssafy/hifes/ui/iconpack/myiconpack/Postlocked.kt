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

public val MyIconPack.Postlocked: ImageVector
    get() {
        if (_postlocked != null) {
            return _postlocked!!
        }
        _postlocked = Builder(name = "Postlocked", defaultWidth = 10.0.dp, defaultHeight = 13.0.dp,
                viewportWidth = 10.0f, viewportHeight = 13.0f).apply {
            path(fill = SolidColor(Color(0xFF9C9C9C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(8.125f, 4.55f)
                verticalLineTo(3.25f)
                curveTo(8.125f, 1.43f, 6.75f, 0.0f, 5.0f, 0.0f)
                curveTo(3.25f, 0.0f, 1.875f, 1.43f, 1.875f, 3.25f)
                verticalLineTo(4.55f)
                curveTo(0.8125f, 4.55f, 0.0f, 5.395f, 0.0f, 6.5f)
                verticalLineTo(11.05f)
                curveTo(0.0f, 12.155f, 0.8125f, 13.0f, 1.875f, 13.0f)
                horizontalLineTo(8.125f)
                curveTo(9.1875f, 13.0f, 10.0f, 12.155f, 10.0f, 11.05f)
                verticalLineTo(6.5f)
                curveTo(10.0f, 5.395f, 9.1875f, 4.55f, 8.125f, 4.55f)
                close()
                moveTo(3.125f, 3.25f)
                curveTo(3.125f, 2.145f, 3.9375f, 1.3f, 5.0f, 1.3f)
                curveTo(6.0625f, 1.3f, 6.875f, 2.145f, 6.875f, 3.25f)
                verticalLineTo(4.55f)
                horizontalLineTo(3.125f)
                verticalLineTo(3.25f)
                close()
            }
        }
        .build()
        return _postlocked!!
    }

private var _postlocked: ImageVector? = null
