package com.ssafy.hifes.ui.iconpack.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack

public val MyIconPack.Leave: ImageVector
    get() {
        if (_leave != null) {
            return _leave!!
        }
        _leave = Builder(name = "Leave", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF979797)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(9.8954f, 11.23f)
                curveTo(9.4579f, 11.23f, 9.1119f, 11.57f, 9.1119f, 12.0f)
                curveTo(9.1119f, 12.42f, 9.4579f, 12.77f, 9.8954f, 12.77f)
                horizontalLineTo(16.0f)
                verticalLineTo(17.55f)
                curveTo(16.0f, 20.0f, 13.9753f, 22.0f, 11.4724f, 22.0f)
                horizontalLineTo(6.5174f)
                curveTo(4.0247f, 22.0f, 2.0f, 20.01f, 2.0f, 17.56f)
                verticalLineTo(6.45f)
                curveTo(2.0f, 3.99f, 4.0349f, 2.0f, 6.5276f, 2.0f)
                horizontalLineTo(11.4927f)
                curveTo(13.9753f, 2.0f, 16.0f, 3.99f, 16.0f, 6.44f)
                verticalLineTo(11.23f)
                horizontalLineTo(9.8954f)
                close()
                moveTo(19.6302f, 8.5402f)
                lineTo(22.5502f, 11.4502f)
                curveTo(22.7002f, 11.6002f, 22.7802f, 11.7902f, 22.7802f, 12.0002f)
                curveTo(22.7802f, 12.2002f, 22.7002f, 12.4002f, 22.5502f, 12.5402f)
                lineTo(19.6302f, 15.4502f)
                curveTo(19.4802f, 15.6002f, 19.2802f, 15.6802f, 19.0902f, 15.6802f)
                curveTo(18.8902f, 15.6802f, 18.6902f, 15.6002f, 18.5402f, 15.4502f)
                curveTo(18.2402f, 15.1502f, 18.2402f, 14.6602f, 18.5402f, 14.3602f)
                lineTo(20.1402f, 12.7702f)
                horizontalLineTo(16.0002f)
                verticalLineTo(11.2302f)
                horizontalLineTo(20.1402f)
                lineTo(18.5402f, 9.6402f)
                curveTo(18.2402f, 9.3402f, 18.2402f, 8.8502f, 18.5402f, 8.5502f)
                curveTo(18.8402f, 8.2402f, 19.3302f, 8.2402f, 19.6302f, 8.5402f)
                close()
            }
        }
        .build()
        return _leave!!
    }

private var _leave: ImageVector? = null
