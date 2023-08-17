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

public val MyIconPack.Notificationon: ImageVector
    get() {
        if (_notificationon != null) {
            return _notificationon!!
        }
        _notificationon = Builder(name = "Notificationon", defaultWidth = 48.0.dp, defaultHeight =
                48.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(185.478f, 774.131f)
                quadToRelative(-16.707f, 0.0f, -28.158f, -11.502f)
                quadToRelative(-11.451f, -11.501f, -11.451f, -28.282f)
                quadToRelative(0.0f, -16.782f, 11.451f, -28.108f)
                reflectiveQuadToRelative(28.158f, -11.326f)
                horizontalLineToRelative(35.304f)
                verticalLineToRelative(-289.869f)
                quadToRelative(0.0f, -88.914f, 51.478f, -160.588f)
                quadToRelative(51.478f, -71.674f, 138.131f, -90.369f)
                verticalLineToRelative(-18.609f)
                quadToRelative(0.0f, -29.239f, 20.302f, -49.707f)
                quadToRelative(20.303f, -20.467f, 49.307f, -20.467f)
                reflectiveQuadToRelative(49.307f, 20.467f)
                quadToRelative(20.302f, 20.468f, 20.302f, 49.707f)
                verticalLineToRelative(18.609f)
                quadToRelative(86.653f, 18.13f, 138.414f, 90.0f)
                quadToRelative(51.761f, 71.87f, 51.761f, 160.957f)
                verticalLineToRelative(289.869f)
                horizontalLineToRelative(34.738f)
                quadToRelative(16.636f, 0.0f, 28.405f, 11.501f)
                quadToRelative(11.769f, 11.502f, 11.769f, 28.283f)
                quadToRelative(0.0f, 16.782f, -11.769f, 28.108f)
                reflectiveQuadToRelative(-28.405f, 11.326f)
                lineTo(185.478f, 774.131f)
                close()
                moveTo(480.0f, 460.261f)
                close()
                moveTo(480.283f, 899.218f)
                quadToRelative(-34.95f, 0.0f, -59.878f, -24.994f)
                quadToRelative(-24.927f, -24.994f, -24.927f, -60.093f)
                horizontalLineToRelative(169.609f)
                quadToRelative(0.0f, 35.261f, -25.044f, 60.174f)
                quadTo(515.0f, 899.218f, 480.283f, 899.218f)
                close()
                moveTo(300.0f, 694.913f)
                horizontalLineToRelative(360.0f)
                verticalLineToRelative(-289.869f)
                quadToRelative(0.0f, -74.435f, -52.5f, -127.217f)
                quadTo(555.0f, 225.044f, 480.0f, 225.044f)
                reflectiveQuadToRelative(-127.5f, 52.783f)
                quadTo(300.0f, 330.609f, 300.0f, 405.044f)
                verticalLineToRelative(289.869f)
                close()
            }
        }
        .build()
        return _notificationon!!
    }

private var _notificationon: ImageVector? = null
