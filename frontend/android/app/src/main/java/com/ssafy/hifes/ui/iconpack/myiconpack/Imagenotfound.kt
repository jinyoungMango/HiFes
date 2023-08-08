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

public val MyIconPack.Imagenotfound: ImageVector
    get() {
        if (_imagenotfound != null) {
            return _imagenotfound!!
        }
        _imagenotfound = Builder(name = "Imagenotfound", defaultWidth = 84.0.dp, defaultHeight =
                84.0.dp, viewportWidth = 84.0f, viewportHeight = 84.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(82.5002f, 64.6248f)
                lineTo(74.1668f, 56.2915f)
                verticalLineTo(9.8332f)
                horizontalLineTo(27.7085f)
                lineTo(19.3752f, 1.4998f)
                horizontalLineTo(74.1668f)
                curveTo(76.4585f, 1.4998f, 78.421f, 2.3165f, 80.0543f, 3.9498f)
                curveTo(81.6877f, 5.5832f, 82.5029f, 7.5443f, 82.5002f, 9.8332f)
                verticalLineTo(64.6248f)
                close()
                moveTo(77.5002f, 83.1665f)
                lineTo(70.8335f, 76.4998f)
                horizontalLineTo(15.8335f)
                curveTo(13.5418f, 76.4998f, 11.5793f, 75.6832f, 9.946f, 74.0498f)
                curveTo(8.3127f, 72.4165f, 7.4974f, 70.4554f, 7.5002f, 68.1665f)
                verticalLineTo(13.1665f)
                lineTo(0.8335f, 6.4998f)
                lineTo(6.6668f, 0.6665f)
                lineTo(83.3335f, 77.3332f)
                lineTo(77.5002f, 83.1665f)
                close()
                moveTo(20.0002f, 59.8332f)
                lineTo(32.5002f, 43.1665f)
                lineTo(41.8752f, 55.6665f)
                lineTo(45.3127f, 51.0832f)
                lineTo(15.8335f, 21.604f)
                verticalLineTo(68.1665f)
                horizontalLineTo(62.396f)
                lineTo(54.0627f, 59.8332f)
                horizontalLineTo(20.0002f)
                close()
            }
        }
        .build()
        return _imagenotfound!!
    }

private var _imagenotfound: ImageVector? = null
