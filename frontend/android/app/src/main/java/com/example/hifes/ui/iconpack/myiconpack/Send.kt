package com.example.hifes.ui.iconpack.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.hifes.ui.iconpack.MyIconPack

public val MyIconPack.Send: ImageVector
    get() {
        if (_send != null) {
            return _send!!
        }
        _send = Builder(name = "Send", defaultWidth = 33.0.dp, defaultHeight = 33.0.dp,
                viewportWidth = 33.0f, viewportHeight = 33.0f).apply {
            path(fill = SolidColor(Color(0xFFC2CCDE)), stroke = SolidColor(Color(0xFF5A5A5A)),
                    fillAlpha = 0.25f, strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin
                    = StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(20.7057f, 9.9944f)
                curveTo(20.7353f, 9.5008f, 20.1888f, 9.1853f, 19.7762f, 9.4577f)
                lineTo(5.9131f, 18.6108f)
                curveTo(5.5409f, 18.8566f, 5.5575f, 19.4081f, 5.9437f, 19.6312f)
                lineTo(10.8185f, 22.4456f)
                lineTo(15.9774f, 17.1102f)
                lineTo(13.9362f, 24.2456f)
                lineTo(18.8115f, 27.0603f)
                curveTo(19.1977f, 27.2834f, 19.6837f, 27.0219f, 19.7104f, 26.5767f)
                lineTo(20.7057f, 9.9944f)
                close()
            }
        }
        .build()
        return _send!!
    }

private var _send: ImageVector? = null
