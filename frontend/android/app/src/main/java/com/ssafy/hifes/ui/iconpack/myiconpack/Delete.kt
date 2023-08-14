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

public val MyIconPack.Delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = Builder(name = "Delete", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(480.0f, 535.566f)
                lineTo(276.783f, 738.783f)
                quadTo(264.957f, 750.609f, 249.0f, 750.609f)
                reflectiveQuadToRelative(-27.783f, -11.826f)
                quadTo(209.391f, 726.957f, 209.391f, 711.0f)
                reflectiveQuadToRelative(11.826f, -27.783f)
                lineTo(424.434f, 480.0f)
                lineTo(221.217f, 276.783f)
                quadTo(209.391f, 264.957f, 209.391f, 249.0f)
                reflectiveQuadToRelative(11.826f, -27.783f)
                quadToRelative(11.826f, -11.826f, 27.783f, -11.826f)
                reflectiveQuadToRelative(27.783f, 11.826f)
                lineTo(480.0f, 424.434f)
                lineToRelative(203.217f, -203.217f)
                quadToRelative(11.826f, -11.826f, 27.783f, -11.826f)
                reflectiveQuadToRelative(27.783f, 11.826f)
                quadToRelative(11.826f, 11.826f, 11.826f, 27.783f)
                reflectiveQuadToRelative(-11.826f, 27.783f)
                lineTo(535.566f, 480.0f)
                lineToRelative(203.217f, 203.217f)
                quadToRelative(11.826f, 11.826f, 11.826f, 27.783f)
                reflectiveQuadToRelative(-11.826f, 27.783f)
                quadTo(726.957f, 750.609f, 711.0f, 750.609f)
                reflectiveQuadToRelative(-27.783f, -11.826f)
                lineTo(480.0f, 535.566f)
                close()
            }
        }
        .build()
        return _delete!!
    }

private var _delete: ImageVector? = null
