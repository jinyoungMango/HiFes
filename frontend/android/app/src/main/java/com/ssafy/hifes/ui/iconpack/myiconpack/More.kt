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
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.ssafy.hifes.ui.iconpack.MyIconPack

public val MyIconPack.More: ImageVector
    get() {
        if (_more != null) {
            return _more!!
        }
        _more = Builder(name = "More", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFECECEC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.0f, 0.0f)
                lineTo(20.0f, 0.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 24.0f, 4.0f)
                lineTo(24.0f, 20.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 20.0f, 24.0f)
                lineTo(4.0f, 24.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 0.0f, 20.0f)
                lineTo(0.0f, 4.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, true, 4.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFBDBDBD)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.0f, 15.875f)
                curveTo(11.0f, 15.6429f, 11.0922f, 15.4204f, 11.2563f, 15.2563f)
                curveTo(11.4204f, 15.0922f, 11.6429f, 15.0f, 11.875f, 15.0f)
                curveTo(12.1071f, 15.0f, 12.3296f, 15.0922f, 12.4937f, 15.2563f)
                curveTo(12.6578f, 15.4204f, 12.75f, 15.6429f, 12.75f, 15.875f)
                curveTo(12.75f, 16.1071f, 12.6578f, 16.3296f, 12.4937f, 16.4937f)
                curveTo(12.3296f, 16.6578f, 12.1071f, 16.75f, 11.875f, 16.75f)
                curveTo(11.6429f, 16.75f, 11.4204f, 16.6578f, 11.2563f, 16.4937f)
                curveTo(11.0922f, 16.3296f, 11.0f, 16.1071f, 11.0f, 15.875f)
                close()
                moveTo(11.0f, 12.375f)
                curveTo(11.0f, 12.1429f, 11.0922f, 11.9204f, 11.2563f, 11.7563f)
                curveTo(11.4204f, 11.5922f, 11.6429f, 11.5f, 11.875f, 11.5f)
                curveTo(12.1071f, 11.5f, 12.3296f, 11.5922f, 12.4937f, 11.7563f)
                curveTo(12.6578f, 11.9204f, 12.75f, 12.1429f, 12.75f, 12.375f)
                curveTo(12.75f, 12.6071f, 12.6578f, 12.8296f, 12.4937f, 12.9937f)
                curveTo(12.3296f, 13.1578f, 12.1071f, 13.25f, 11.875f, 13.25f)
                curveTo(11.6429f, 13.25f, 11.4204f, 13.1578f, 11.2563f, 12.9937f)
                curveTo(11.0922f, 12.8296f, 11.0f, 12.6071f, 11.0f, 12.375f)
                close()
                moveTo(11.0f, 8.875f)
                curveTo(11.0f, 8.6429f, 11.0922f, 8.4204f, 11.2563f, 8.2563f)
                curveTo(11.4204f, 8.0922f, 11.6429f, 8.0f, 11.875f, 8.0f)
                curveTo(12.1071f, 8.0f, 12.3296f, 8.0922f, 12.4937f, 8.2563f)
                curveTo(12.6578f, 8.4204f, 12.75f, 8.6429f, 12.75f, 8.875f)
                curveTo(12.75f, 9.1071f, 12.6578f, 9.3296f, 12.4937f, 9.4937f)
                curveTo(12.3296f, 9.6578f, 12.1071f, 9.75f, 11.875f, 9.75f)
                curveTo(11.6429f, 9.75f, 11.4204f, 9.6578f, 11.2563f, 9.4937f)
                curveTo(11.0922f, 9.3296f, 11.0f, 9.1071f, 11.0f, 8.875f)
                close()
            }
        }
        .build()
        return _more!!
    }

private var _more: ImageVector? = null
