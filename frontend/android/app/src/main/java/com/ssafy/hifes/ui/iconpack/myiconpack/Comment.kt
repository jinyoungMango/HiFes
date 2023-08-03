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

public val MyIconPack.Comment: ImageVector
    get() {
        if (_comment != null) {
            return _comment!!
        }
        _comment = Builder(name = "Comment", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFECECEC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 4.0f)
                curveTo(0.0f, 1.7909f, 1.7909f, 0.0f, 4.0f, 0.0f)
                horizontalLineTo(20.0f)
                curveTo(22.2091f, 0.0f, 24.0f, 1.7909f, 24.0f, 4.0f)
                verticalLineTo(20.0f)
                curveTo(24.0f, 22.2091f, 22.2091f, 24.0f, 20.0f, 24.0f)
                horizontalLineTo(4.0f)
                curveTo(1.7909f, 24.0f, 0.0f, 22.2091f, 0.0f, 20.0f)
                verticalLineTo(4.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFBDBDBD)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(7.3432f, 14.5323f)
                curveTo(7.4341f, 14.6234f, 7.5037f, 14.7335f, 7.5471f, 14.8546f)
                curveTo(7.5905f, 14.9758f, 7.6066f, 15.105f, 7.5944f, 15.2331f)
                curveTo(7.5329f, 15.826f, 7.4163f, 16.4119f, 7.2461f, 16.9831f)
                curveTo(8.4668f, 16.7005f, 9.2122f, 16.3733f, 9.5509f, 16.2018f)
                curveTo(9.7429f, 16.1045f, 9.9641f, 16.0814f, 10.1721f, 16.137f)
                curveTo(10.7684f, 16.296f, 11.3829f, 16.376f, 12.0f, 16.375f)
                curveTo(15.4965f, 16.375f, 18.125f, 13.9189f, 18.125f, 11.125f)
                curveTo(18.125f, 8.332f, 15.4965f, 5.875f, 12.0f, 5.875f)
                curveTo(8.5035f, 5.875f, 5.875f, 8.332f, 5.875f, 11.125f)
                curveTo(5.875f, 12.4095f, 6.4149f, 13.6013f, 7.3432f, 14.5323f)
                close()
                moveTo(6.9119f, 17.9491f)
                curveTo(6.7046f, 17.9902f, 6.4966f, 18.0279f, 6.288f, 18.062f)
                curveTo(6.113f, 18.09f, 5.98f, 17.908f, 6.0491f, 17.7452f)
                curveTo(6.1268f, 17.562f, 6.198f, 17.3761f, 6.2626f, 17.1879f)
                lineTo(6.2653f, 17.1791f)
                curveTo(6.4823f, 16.5491f, 6.659f, 15.8246f, 6.7238f, 15.15f)
                curveTo(5.6501f, 14.0738f, 5.0f, 12.665f, 5.0f, 11.125f)
                curveTo(5.0f, 7.7422f, 8.1342f, 5.0f, 12.0f, 5.0f)
                curveTo(15.8658f, 5.0f, 19.0f, 7.7422f, 19.0f, 11.125f)
                curveTo(19.0f, 14.5077f, 15.8658f, 17.25f, 12.0f, 17.25f)
                curveTo(11.3067f, 17.2509f, 10.6163f, 17.1609f, 9.9464f, 16.9823f)
                curveTo(9.4914f, 17.2124f, 8.5122f, 17.6315f, 6.9119f, 17.9491f)
                close()
            }
        }
        .build()
        return _comment!!
    }

private var _comment: ImageVector? = null
