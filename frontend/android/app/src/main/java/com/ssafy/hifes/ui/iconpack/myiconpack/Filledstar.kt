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

public val MyIconPack.Filledstar: ImageVector
    get() {
        if (_filledstar != null) {
            return _filledstar!!
        }
        _filledstar = Builder(name = "Filledstar", defaultWidth = 15.0.dp, defaultHeight = 14.0.dp,
                viewportWidth = 15.0f, viewportHeight = 14.0f).apply {
            path(fill = SolidColor(Color(0xFFF11A7B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(7.4873f, 11.4146f)
                lineTo(11.1186f, 13.5063f)
                curveTo(11.7836f, 13.8896f, 12.5973f, 13.3229f, 12.4223f, 12.6063f)
                lineTo(11.4598f, 8.6729f)
                lineTo(14.6711f, 6.0229f)
                curveTo(15.2573f, 5.5396f, 14.9423f, 4.6229f, 14.1723f, 4.5646f)
                lineTo(9.9461f, 4.2229f)
                lineTo(8.2923f, 0.5063f)
                curveTo(7.9948f, -0.1688f, 6.9798f, -0.1688f, 6.6823f, 0.5063f)
                lineTo(5.0286f, 4.2146f)
                lineTo(0.8023f, 4.5563f)
                curveTo(0.0323f, 4.6146f, -0.2827f, 5.5313f, 0.3036f, 6.0146f)
                lineTo(3.5148f, 8.6646f)
                lineTo(2.5523f, 12.5979f)
                curveTo(2.3773f, 13.3146f, 3.1911f, 13.8813f, 3.8561f, 13.4979f)
                lineTo(7.4873f, 11.4146f)
                close()
            }
        }
        .build()
        return _filledstar!!
    }

private var _filledstar: ImageVector? = null
