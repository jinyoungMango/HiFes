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

public val MyIconPack.Postunlocked: ImageVector
    get() {
        if (_postunlocked != null) {
            return _postunlocked!!
        }
        _postunlocked = Builder(name = "Postunlocked", defaultWidth = 11.0.dp, defaultHeight =
                13.0.dp, viewportWidth = 11.0f, viewportHeight = 13.0f).apply {
            path(fill = SolidColor(Color(0xFF9C9C9C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(8.9375f, 4.55f)
                horizontalLineTo(3.4375f)
                verticalLineTo(3.25f)
                curveTo(3.4375f, 2.73f, 3.6437f, 2.275f, 4.0563f, 1.885f)
                curveTo(4.8812f, 1.105f, 6.1875f, 1.105f, 6.9437f, 1.885f)
                curveTo(7.2188f, 2.145f, 7.3562f, 2.47f, 7.4938f, 2.795f)
                curveTo(7.5625f, 3.12f, 7.975f, 3.315f, 8.3188f, 3.25f)
                curveTo(8.6625f, 3.185f, 8.9375f, 2.795f, 8.8f, 2.47f)
                curveTo(8.6625f, 1.885f, 8.3188f, 1.365f, 7.9063f, 0.975f)
                curveTo(7.2875f, 0.325f, 6.3938f, 0.0f, 5.5f, 0.0f)
                curveTo(3.575f, 0.0f, 2.0625f, 1.43f, 2.0625f, 3.25f)
                verticalLineTo(4.55f)
                curveTo(0.8938f, 4.55f, 0.0f, 5.395f, 0.0f, 6.5f)
                verticalLineTo(11.05f)
                curveTo(0.0f, 12.155f, 0.8938f, 13.0f, 2.0625f, 13.0f)
                horizontalLineTo(8.9375f)
                curveTo(10.1062f, 13.0f, 11.0f, 12.155f, 11.0f, 11.05f)
                verticalLineTo(6.5f)
                curveTo(11.0f, 5.395f, 10.1062f, 4.55f, 8.9375f, 4.55f)
                close()
            }
        }
        .build()
        return _postunlocked!!
    }

private var _postunlocked: ImageVector? = null
