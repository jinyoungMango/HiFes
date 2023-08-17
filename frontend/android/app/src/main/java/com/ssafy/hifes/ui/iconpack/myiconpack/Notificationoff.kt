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

public val MyIconPack.Notificationoff: ImageVector
    get() {
        if (_notificationoff != null) {
            return _notificationoff!!
        }
        _notificationoff = Builder(name = "Notificationoff", defaultWidth = 48.0.dp, defaultHeight =
                48.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(685.218f, 774.131f)
                horizontalLineToRelative(-499.74f)
                quadToRelative(-16.707f, 0.0f, -28.158f, -11.502f)
                quadToRelative(-11.451f, -11.501f, -11.451f, -28.282f)
                quadToRelative(0.0f, -16.782f, 11.451f, -28.108f)
                reflectiveQuadToRelative(28.158f, -11.326f)
                horizontalLineToRelative(35.304f)
                verticalLineToRelative(-289.869f)
                quadToRelative(0.0f, -38.653f, 10.228f, -75.74f)
                quadToRelative(10.228f, -37.087f, 31.25f, -70.609f)
                lineToRelative(59.131f, 59.696f)
                quadToRelative(-10.695f, 20.131f, -16.043f, 42.264f)
                quadTo(300.0f, 382.788f, 300.0f, 405.044f)
                verticalLineToRelative(289.869f)
                horizontalLineToRelative(304.869f)
                lineTo(86.956f, 176.0f)
                quadToRelative(-10.13f, -10.13f, -9.848f, -23.261f)
                quadToRelative(0.283f, -13.13f, 10.413f, -23.261f)
                quadToRelative(10.13f, -9.565f, 23.261f, -9.565f)
                quadToRelative(13.131f, 0.0f, 23.261f, 9.565f)
                lineToRelative(699.262f, 700.827f)
                quadToRelative(10.13f, 10.13f, 10.13f, 23.261f)
                quadToRelative(0.0f, 13.13f, -10.13f, 23.26f)
                quadToRelative(-10.131f, 10.131f, -23.261f, 10.131f)
                quadToRelative(-13.131f, 0.0f, -23.261f, -10.13f)
                lineTo(685.218f, 774.13f)
                close()
                moveTo(739.784f, 641.74f)
                lineTo(660.0f, 561.956f)
                verticalLineToRelative(-156.912f)
                quadToRelative(0.0f, -74.435f, -52.5f, -127.217f)
                quadTo(555.0f, 225.044f, 480.0f, 225.044f)
                quadToRelative(-32.309f, 0.0f, -61.632f, 11.022f)
                quadToRelative(-29.324f, 11.021f, -51.063f, 33.195f)
                lineToRelative(-61.262f, -60.262f)
                quadToRelative(22.435f, -20.739f, 48.239f, -34.891f)
                quadToRelative(25.805f, -14.152f, 56.109f, -20.154f)
                verticalLineToRelative(-18.476f)
                quadToRelative(0.0f, -29.286f, 20.444f, -49.73f)
                quadToRelative(20.444f, -20.444f, 49.165f, -20.444f)
                reflectiveQuadToRelative(49.165f, 20.444f)
                quadToRelative(20.444f, 20.444f, 20.444f, 49.73f)
                verticalLineToRelative(18.609f)
                quadToRelative(86.653f, 17.565f, 138.414f, 89.717f)
                quadToRelative(51.761f, 72.153f, 51.761f, 161.24f)
                verticalLineToRelative(236.696f)
                close()
                moveTo(453.217f, 541.696f)
                close()
                moveTo(480.565f, 899.218f)
                quadToRelative(-35.232f, 0.0f, -60.16f, -25.043f)
                quadToRelative(-24.927f, -25.044f, -24.927f, -60.044f)
                horizontalLineToRelative(169.609f)
                quadToRelative(0.0f, 35.261f, -25.044f, 60.174f)
                quadTo(515.0f, 899.218f, 480.565f, 899.218f)
                close()
                moveTo(513.652f, 415.609f)
                close()
            }
        }
        .build()
        return _notificationoff!!
    }

private var _notificationoff: ImageVector? = null
