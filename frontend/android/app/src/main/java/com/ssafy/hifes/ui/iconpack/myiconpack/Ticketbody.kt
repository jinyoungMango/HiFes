package myiconpack

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

public val MyIconPack.Ticketbody: ImageVector
    get() {
        if (_ticketbody != null) {
            return _ticketbody!!
        }
        _ticketbody = Builder(name = "Ticketbody", defaultWidth = 241.0.dp, defaultHeight =
                121.0.dp, viewportWidth = 241.0f, viewportHeight = 121.0f).apply {
            path(fill = SolidColor(Color(0xFFFFF8EA)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(0.0f, 0.0f)
                horizontalLineTo(241.0f)
                verticalLineTo(9.071f)
                lineTo(234.0f, 16.071f)
                lineTo(241.0f, 23.071f)
                verticalLineTo(31.071f)
                lineTo(234.0f, 38.071f)
                lineTo(241.0f, 45.071f)
                verticalLineTo(53.071f)
                lineTo(234.0f, 60.071f)
                lineTo(241.0f, 67.071f)
                verticalLineTo(75.071f)
                lineTo(234.0f, 82.071f)
                lineTo(241.0f, 89.071f)
                verticalLineTo(97.071f)
                lineTo(234.0f, 104.071f)
                lineTo(241.0f, 111.071f)
                verticalLineTo(121.0f)
                horizontalLineTo(0.0f)
                verticalLineTo(85.0f)
                curveTo(13.255f, 85.0f, 24.0f, 74.255f, 24.0f, 61.0f)
                curveTo(24.0f, 47.745f, 13.255f, 37.0f, 0.0f, 37.0f)
                verticalLineTo(0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFF11A7B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(11.0f, 11.0f)
                horizontalLineTo(225.0f)
                verticalLineTo(110.0f)
                horizontalLineTo(11.0f)
                verticalLineTo(82.336f)
                curveTo(10.35f, 82.672f, 9.683f, 82.979f, 9.0f, 83.255f)
                verticalLineTo(110.0f)
                verticalLineTo(112.0f)
                horizontalLineTo(11.0f)
                horizontalLineTo(225.0f)
                horizontalLineTo(227.0f)
                verticalLineTo(110.0f)
                verticalLineTo(11.0f)
                verticalLineTo(9.0f)
                horizontalLineTo(225.0f)
                horizontalLineTo(11.0f)
                horizontalLineTo(9.0f)
                verticalLineTo(11.0f)
                verticalLineTo(38.745f)
                curveTo(9.683f, 39.021f, 10.35f, 39.328f, 11.0f, 39.664f)
                verticalLineTo(11.0f)
                close()
            }
        }
        .build()
        return _ticketbody!!
    }

private var _ticketbody: ImageVector? = null
