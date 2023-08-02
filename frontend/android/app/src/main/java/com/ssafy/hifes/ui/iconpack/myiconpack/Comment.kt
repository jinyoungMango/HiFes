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

public val MyIconPack.Comment: ImageVector
    get() {
        if (_comment != null) {
            return _comment!!
        }
        _comment = Builder(name = "Comment", defaultWidth = 14.0.dp, defaultHeight = 14.0.dp,
                viewportWidth = 14.0f, viewportHeight = 14.0f).apply {
            path(fill = SolidColor(Color(0xFFBDBDBD)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.3433f, 9.5323f)
                curveTo(2.4341f, 9.6234f, 2.5037f, 9.7335f, 2.5471f, 9.8546f)
                curveTo(2.5905f, 9.9758f, 2.6066f, 10.105f, 2.5944f, 10.2331f)
                curveTo(2.5329f, 10.826f, 2.4163f, 11.4119f, 2.2461f, 11.9831f)
                curveTo(3.4667f, 11.7005f, 4.2123f, 11.3733f, 4.5509f, 11.2017f)
                curveTo(4.7429f, 11.1045f, 4.9641f, 11.0814f, 5.1721f, 11.137f)
                curveTo(5.7684f, 11.296f, 6.3829f, 11.376f, 7.0f, 11.375f)
                curveTo(10.4965f, 11.375f, 13.125f, 8.9189f, 13.125f, 6.125f)
                curveTo(13.125f, 3.332f, 10.4965f, 0.875f, 7.0f, 0.875f)
                curveTo(3.5035f, 0.875f, 0.875f, 3.332f, 0.875f, 6.125f)
                curveTo(0.875f, 7.4095f, 1.4149f, 8.6012f, 2.3433f, 9.5323f)
                close()
                moveTo(1.9119f, 12.9491f)
                curveTo(1.7046f, 12.9902f, 1.4966f, 13.0279f, 1.288f, 13.062f)
                curveTo(1.113f, 13.09f, 0.98f, 12.908f, 1.0491f, 12.7452f)
                curveTo(1.1268f, 12.562f, 1.198f, 12.3761f, 1.2626f, 12.1879f)
                lineTo(1.2652f, 12.1791f)
                curveTo(1.4822f, 11.5491f, 1.659f, 10.8246f, 1.7237f, 10.15f)
                curveTo(0.6501f, 9.0737f, 0.0f, 7.665f, 0.0f, 6.125f)
                curveTo(0.0f, 2.7422f, 3.1342f, 0.0f, 7.0f, 0.0f)
                curveTo(10.8658f, 0.0f, 14.0f, 2.7422f, 14.0f, 6.125f)
                curveTo(14.0f, 9.5077f, 10.8658f, 12.25f, 7.0f, 12.25f)
                curveTo(6.3067f, 12.2509f, 5.6163f, 12.1609f, 4.9464f, 11.9823f)
                curveTo(4.4914f, 12.2124f, 3.5122f, 12.6315f, 1.9119f, 12.9491f)
                close()
            }
        }
        .build()
        return _comment!!
    }

private var _comment: ImageVector? = null
