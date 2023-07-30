package com.example.hifes.ui.iconpack.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.hifes.ui.iconpack.MyIconPack

public val MyIconPack.Camera: ImageVector
    get() {
        if (_camera != null) {
            return _camera!!
        }
        _camera = Builder(name = "Camera", defaultWidth = 36.0.dp, defaultHeight = 36.0.dp,
                viewportWidth = 36.0f, viewportHeight = 36.0f).apply {
            path(fill = SolidColor(Color(0xFFF11A7B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.5f, 17.5f)
                moveToRelative(17.5f, 0.0f)
                arcToRelative(17.5f, 17.5f, 0.0f, true, false, -35.0f, 0.0f)
                arcToRelative(17.5f, 17.5f, 0.0f, true, false, 35.0f, 0.0f)
            }
            path(fill = SolidColor(Color(0xFFC2CCDE)), stroke = null, fillAlpha = 0.25f,
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(14.7901f, 13.7026f)
                lineTo(14.95f, 13.3142f)
                curveTo(15.2586f, 12.5648f, 15.9889f, 12.0757f, 16.7994f, 12.0757f)
                horizontalLineTo(19.4106f)
                curveTo(20.2211f, 12.0757f, 20.9514f, 12.5648f, 21.26f, 13.3142f)
                lineTo(21.4199f, 13.7026f)
                horizontalLineTo(22.0f)
                curveTo(23.6569f, 13.7026f, 25.0f, 15.0457f, 25.0f, 16.7026f)
                verticalLineTo(19.9992f)
                curveTo(25.0f, 21.656f, 23.6569f, 22.9992f, 22.0f, 22.9992f)
                horizontalLineTo(14.0f)
                curveTo(12.3431f, 22.9992f, 11.0f, 21.656f, 11.0f, 19.9992f)
                lineTo(11.0f, 16.7026f)
                curveTo(11.0f, 15.0457f, 12.3431f, 13.7026f, 14.0f, 13.7026f)
                horizontalLineTo(14.7901f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(14.7901f, 13.7026f)
                verticalLineTo(14.2026f)
                horizontalLineTo(15.1249f)
                lineTo(15.2524f, 13.893f)
                lineTo(14.7901f, 13.7026f)
                close()
                moveTo(14.95f, 13.3142f)
                lineTo(15.4123f, 13.5046f)
                lineTo(14.95f, 13.3142f)
                close()
                moveTo(21.26f, 13.3142f)
                lineTo(21.7223f, 13.1239f)
                verticalLineTo(13.1239f)
                lineTo(21.26f, 13.3142f)
                close()
                moveTo(21.4199f, 13.7026f)
                lineTo(20.9575f, 13.893f)
                lineTo(21.085f, 14.2026f)
                horizontalLineTo(21.4199f)
                verticalLineTo(13.7026f)
                close()
                moveTo(15.2524f, 13.893f)
                lineTo(15.4123f, 13.5046f)
                lineTo(14.4876f, 13.1239f)
                lineTo(14.3277f, 13.5122f)
                lineTo(15.2524f, 13.893f)
                close()
                moveTo(15.4123f, 13.5046f)
                curveTo(15.6438f, 12.9425f, 16.1915f, 12.5757f, 16.7994f, 12.5757f)
                verticalLineTo(11.5757f)
                curveTo(15.7863f, 11.5757f, 14.8734f, 12.1871f, 14.4876f, 13.1239f)
                lineTo(15.4123f, 13.5046f)
                close()
                moveTo(16.7994f, 12.5757f)
                lineTo(19.4106f, 12.5757f)
                verticalLineTo(11.5757f)
                horizontalLineTo(16.7994f)
                verticalLineTo(12.5757f)
                close()
                moveTo(19.4106f, 12.5757f)
                curveTo(20.0184f, 12.5757f, 20.5662f, 12.9425f, 20.7976f, 13.5046f)
                lineTo(21.7223f, 13.1239f)
                curveTo(21.3366f, 12.1871f, 20.4237f, 11.5757f, 19.4106f, 11.5757f)
                verticalLineTo(12.5757f)
                close()
                moveTo(20.7976f, 13.5046f)
                lineTo(20.9575f, 13.893f)
                lineTo(21.8822f, 13.5122f)
                lineTo(21.7223f, 13.1239f)
                lineTo(20.7976f, 13.5046f)
                close()
                moveTo(21.4199f, 14.2026f)
                horizontalLineTo(22.0f)
                verticalLineTo(13.2026f)
                horizontalLineTo(21.4199f)
                verticalLineTo(14.2026f)
                close()
                moveTo(22.0f, 14.2026f)
                curveTo(23.3807f, 14.2026f, 24.5f, 15.3219f, 24.5f, 16.7026f)
                lineTo(25.5f, 16.7026f)
                curveTo(25.5f, 14.7696f, 23.933f, 13.2026f, 22.0f, 13.2026f)
                verticalLineTo(14.2026f)
                close()
                moveTo(24.5f, 16.7026f)
                verticalLineTo(19.9992f)
                lineTo(25.5f, 19.9992f)
                verticalLineTo(16.7026f)
                lineTo(24.5f, 16.7026f)
                close()
                moveTo(24.5f, 19.9992f)
                curveTo(24.5f, 21.3799f, 23.3807f, 22.4992f, 22.0f, 22.4992f)
                verticalLineTo(23.4992f)
                curveTo(23.933f, 23.4992f, 25.5f, 21.9322f, 25.5f, 19.9992f)
                lineTo(24.5f, 19.9992f)
                close()
                moveTo(22.0f, 22.4992f)
                horizontalLineTo(14.0f)
                verticalLineTo(23.4992f)
                horizontalLineTo(22.0f)
                verticalLineTo(22.4992f)
                close()
                moveTo(14.0f, 22.4992f)
                curveTo(12.6193f, 22.4992f, 11.5f, 21.3799f, 11.5f, 19.9992f)
                horizontalLineTo(10.5f)
                curveTo(10.5f, 21.9322f, 12.067f, 23.4992f, 14.0f, 23.4992f)
                verticalLineTo(22.4992f)
                close()
                moveTo(11.5f, 19.9992f)
                lineTo(11.5f, 16.7026f)
                horizontalLineTo(10.5f)
                lineTo(10.5f, 19.9992f)
                horizontalLineTo(11.5f)
                close()
                moveTo(11.5f, 16.7026f)
                curveTo(11.5f, 15.3219f, 12.6193f, 14.2026f, 14.0f, 14.2026f)
                verticalLineTo(13.2026f)
                curveTo(12.067f, 13.2026f, 10.5f, 14.7696f, 10.5f, 16.7026f)
                horizontalLineTo(11.5f)
                close()
                moveTo(14.0f, 14.2026f)
                horizontalLineTo(14.7901f)
                verticalLineTo(13.2026f)
                horizontalLineTo(14.0f)
                verticalLineTo(14.2026f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFffffff)),
                    strokeLineWidth = 1.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(16.5665f, 15.6341f)
                curveTo(17.4618f, 15.1536f, 18.5382f, 15.1536f, 19.4335f, 15.6341f)
                lineTo(19.5976f, 15.7222f)
                curveTo(20.4803f, 16.196f, 21.0311f, 17.1167f, 21.0311f, 18.1185f)
                verticalLineTo(18.1185f)
                curveTo(21.0311f, 19.1204f, 20.4803f, 20.0411f, 19.5976f, 20.5148f)
                lineTo(19.4335f, 20.603f)
                curveTo(18.5382f, 21.0835f, 17.4618f, 21.0835f, 16.5665f, 20.603f)
                lineTo(16.4024f, 20.5148f)
                curveTo(15.5197f, 20.0411f, 14.9689f, 19.1204f, 14.9689f, 18.1185f)
                verticalLineTo(18.1185f)
                curveTo(14.9689f, 17.1167f, 15.5197f, 16.196f, 16.4024f, 15.7222f)
                lineTo(16.5665f, 15.6341f)
                close()
            }
        }
        .build()
        return _camera!!
    }

private var _camera: ImageVector? = null
