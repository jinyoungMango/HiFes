package com.example.hifes.ui.iconpack.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.hifes.ui.iconpack.MyIconPack

public val MyIconPack.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search = Builder(name = "Search", defaultWidth = 25.0.dp, defaultHeight = 25.0.dp,
                viewportWidth = 25.0f, viewportHeight = 25.0f).apply {
            path(fill = SolidColor(Color(0xFFF11A7B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(24.6168f, 22.4906f)
                lineTo(18.9495f, 16.8232f)
                curveTo(20.3139f, 15.0068f, 21.0505f, 12.7958f, 21.048f, 10.524f)
                curveTo(21.048f, 4.7211f, 16.3269f, 0.0f, 10.524f, 0.0f)
                curveTo(4.7211f, 0.0f, 0.0f, 4.7211f, 0.0f, 10.524f)
                curveTo(0.0f, 16.3269f, 4.7211f, 21.048f, 10.524f, 21.048f)
                curveTo(12.7958f, 21.0505f, 15.0068f, 20.3139f, 16.8232f, 18.9495f)
                lineTo(22.4906f, 24.6168f)
                curveTo(22.7775f, 24.8733f, 23.1516f, 25.0102f, 23.5363f, 24.9994f)
                curveTo(23.9209f, 24.9886f, 24.2869f, 24.831f, 24.5589f, 24.5589f)
                curveTo(24.831f, 24.2869f, 24.9886f, 23.9209f, 24.9994f, 23.5363f)
                curveTo(25.0102f, 23.1516f, 24.8733f, 22.7775f, 24.6168f, 22.4906f)
                close()
                moveTo(3.0069f, 10.524f)
                curveTo(3.0069f, 9.0372f, 3.4477f, 7.5839f, 4.2737f, 6.3477f)
                curveTo(5.0997f, 5.1115f, 6.2737f, 4.148f, 7.6473f, 3.5791f)
                curveTo(9.0209f, 3.0101f, 10.5323f, 2.8612f, 11.9905f, 3.1513f)
                curveTo(13.4487f, 3.4413f, 14.7881f, 4.1573f, 15.8394f, 5.2086f)
                curveTo(16.8907f, 6.2599f, 17.6067f, 7.5993f, 17.8967f, 9.0575f)
                curveTo(18.1868f, 10.5157f, 18.0379f, 12.0271f, 17.4689f, 13.4007f)
                curveTo(16.9f, 14.7743f, 15.9365f, 15.9483f, 14.7003f, 16.7743f)
                curveTo(13.4641f, 17.6003f, 12.0107f, 18.0411f, 10.524f, 18.0411f)
                curveTo(8.5311f, 18.0387f, 6.6204f, 17.246f, 5.2112f, 15.8368f)
                curveTo(3.802f, 14.4276f, 3.0092f, 12.5169f, 3.0069f, 10.524f)
                close()
            }
        }
        .build()
        return _search!!
    }

private var _search: ImageVector? = null
