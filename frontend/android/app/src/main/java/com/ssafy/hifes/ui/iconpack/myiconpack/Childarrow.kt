package com.ssafy.hifes.ui.iconpack.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack

public val MyIconPack.Childarrow: ImageVector
    get() {
        if (_childarrow != null) {
            return _childarrow!!
        }
        _childarrow = Builder(name = "Childarrow", defaultWidth = 16.0.dp, defaultHeight = 16.0.dp,
                viewportWidth = 16.0f, viewportHeight = 16.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(0.0f, 0.0f)
                    horizontalLineToRelative(16.0f)
                    verticalLineToRelative(16.0f)
                    horizontalLineToRelative(-16.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFBDBDBD)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(11.0311f, 7.022f)
                    curveTo(10.9478f, 6.9392f, 10.8352f, 6.8928f, 10.7178f, 6.8928f)
                    curveTo(10.6003f, 6.8928f, 10.4877f, 6.9392f, 10.4044f, 7.022f)
                    curveTo(10.3216f, 7.1053f, 10.2752f, 7.2179f, 10.2752f, 7.3354f)
                    curveTo(10.2752f, 7.4528f, 10.3216f, 7.5654f, 10.4044f, 7.6487f)
                    lineTo(12.0889f, 9.3331f)
                    horizontalLineTo(4.0f)
                    verticalLineTo(1.6798f)
                    curveTo(4.0f, 1.5619f, 3.9532f, 1.4489f, 3.8698f, 1.3655f)
                    curveTo(3.7864f, 1.2822f, 3.6734f, 1.2354f, 3.5555f, 1.2354f)
                    curveTo(3.4376f, 1.2354f, 3.3246f, 1.2822f, 3.2413f, 1.3655f)
                    curveTo(3.1579f, 1.4489f, 3.1111f, 1.5619f, 3.1111f, 1.6798f)
                    verticalLineTo(9.3331f)
                    curveTo(3.1111f, 9.5689f, 3.2047f, 9.795f, 3.3714f, 9.9617f)
                    curveTo(3.5381f, 10.1284f, 3.7642f, 10.222f, 4.0f, 10.222f)
                    horizontalLineTo(12.0666f)
                    lineTo(10.4044f, 11.8887f)
                    curveTo(10.3216f, 11.972f, 10.2752f, 12.0846f, 10.2752f, 12.202f)
                    curveTo(10.2752f, 12.3194f, 10.3216f, 12.4321f, 10.4044f, 12.5154f)
                    curveTo(10.4872f, 12.5975f, 10.5989f, 12.6437f, 10.7155f, 12.6442f)
                    curveTo(10.774f, 12.6446f, 10.832f, 12.6334f, 10.8862f, 12.6112f)
                    curveTo(10.9403f, 12.5891f, 10.9896f, 12.5565f, 11.0311f, 12.5154f)
                    lineTo(13.7778f, 9.7776f)
                    lineTo(11.0311f, 7.022f)
                    close()
                }
            }
        }
        .build()
        return _childarrow!!
    }

private var _childarrow: ImageVector? = null
