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

public val MyIconPack.Halffilledstar: ImageVector
    get() {
        if (_halffilledstar != null) {
            return _halffilledstar!!
        }
        _halffilledstar = Builder(name = "Halffilledstar", defaultWidth = 15.0.dp, defaultHeight =
                14.0.dp, viewportWidth = 15.0f, viewportHeight = 14.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(14.9166f, 5.13f)
                curveTo(14.8519f, 4.9416f, 14.7299f, 4.7753f, 14.5658f, 4.6518f)
                curveTo(14.4018f, 4.5284f, 14.2029f, 4.4533f, 13.9939f, 4.4358f)
                lineTo(10.0259f, 4.1127f)
                lineTo(8.4705f, 0.619f)
                curveTo(8.3896f, 0.4357f, 8.253f, 0.2791f, 8.078f, 0.1689f)
                curveTo(7.903f, 0.0588f, 7.6974f, 0.0f, 7.4871f, 0.0f)
                curveTo(7.2768f, 0.0f, 7.0712f, 0.0588f, 6.8962f, 0.1689f)
                curveTo(6.7212f, 0.2791f, 6.5846f, 0.4357f, 6.5036f, 0.619f)
                lineTo(4.9529f, 4.1127f)
                lineTo(0.9802f, 4.4371f)
                curveTo(0.7703f, 4.4536f, 0.5702f, 4.5284f, 0.4051f, 4.6519f)
                curveTo(0.24f, 4.7755f, 0.1172f, 4.9424f, 0.0522f, 5.1316f)
                curveTo(-0.0128f, 5.3209f, -0.0172f, 5.5241f, 0.0396f, 5.7157f)
                curveTo(0.0964f, 5.9073f, 0.2119f, 6.0788f, 0.3715f, 6.2086f)
                lineTo(3.3854f, 8.6964f)
                lineTo(2.4821f, 12.3908f)
                curveTo(2.4344f, 12.5841f, 2.448f, 12.7864f, 2.5213f, 12.9725f)
                curveTo(2.5946f, 13.1586f, 2.7244f, 13.3201f, 2.8944f, 13.4368f)
                curveTo(3.0643f, 13.5536f, 3.267f, 13.6205f, 3.477f, 13.6291f)
                curveTo(3.6869f, 13.6376f, 3.8949f, 13.5876f, 4.075f, 13.4851f)
                lineTo(7.4824f, 11.5287f)
                lineTo(10.8972f, 13.4851f)
                curveTo(11.0772f, 13.5876f, 11.2852f, 13.6376f, 11.4952f, 13.6291f)
                curveTo(11.7052f, 13.6205f, 11.9078f, 13.5536f, 12.0778f, 13.4368f)
                curveTo(12.2477f, 13.3201f, 12.3775f, 13.1586f, 12.4508f, 12.9725f)
                curveTo(12.5241f, 12.7864f, 12.5378f, 12.5841f, 12.49f, 12.3908f)
                lineTo(11.5874f, 8.6926f)
                lineTo(14.6006f, 6.2086f)
                curveTo(14.76f, 6.0783f, 14.875f, 5.9064f, 14.9313f, 5.7144f)
                curveTo(14.9875f, 5.5225f, 14.9824f, 5.3192f, 14.9166f, 5.13f)
                close()
                moveTo(13.8997f, 5.4456f)
                lineTo(10.8865f, 7.9296f)
                curveTo(10.7398f, 8.0501f, 10.6307f, 8.206f, 10.5709f, 8.3807f)
                curveTo(10.5111f, 8.5554f, 10.5029f, 8.7422f, 10.5471f, 8.921f)
                lineTo(11.4524f, 12.6218f)
                lineTo(8.0403f, 10.6654f)
                curveTo(7.8738f, 10.5696f, 7.6827f, 10.5189f, 7.4877f, 10.5189f)
                verticalLineTo(1.0153f)
                lineTo(9.0378f, 4.504f)
                curveTo(9.1131f, 4.6735f, 9.2359f, 4.8204f, 9.3931f, 4.9289f)
                curveTo(9.5503f, 5.0373f, 9.7359f, 5.1032f, 9.9297f, 5.1193f)
                lineTo(13.9011f, 5.4437f)
                verticalLineTo(5.4481f)
                lineTo(13.8997f, 5.4456f)
                close()
            }
        }
        .build()
        return _halffilledstar!!
    }

private var _halffilledstar: ImageVector? = null
