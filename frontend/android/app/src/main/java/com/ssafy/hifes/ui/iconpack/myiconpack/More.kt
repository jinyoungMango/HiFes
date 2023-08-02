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

public val MyIconPack.More: ImageVector
    get() {
        if (_more != null) {
            return _more!!
        }
        _more = Builder(name = "More", defaultWidth = 2.0.dp, defaultHeight = 9.0.dp, viewportWidth
                = 2.0f, viewportHeight = 9.0f).apply {
            path(fill = SolidColor(Color(0xFFBDBDBD)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 7.875f)
                curveTo(0.0f, 7.64294f, 0.09219f, 7.42038f, 0.25628f, 7.25628f)
                curveTo(0.42038f, 7.09219f, 0.64294f, 7.0f, 0.875f, 7.0f)
                curveTo(1.10706f, 7.0f, 1.32962f, 7.09219f, 1.49372f, 7.25628f)
                curveTo(1.65781f, 7.42038f, 1.75f, 7.64294f, 1.75f, 7.875f)
                curveTo(1.75f, 8.10706f, 1.65781f, 8.32962f, 1.49372f, 8.49372f)
                curveTo(1.32962f, 8.65781f, 1.10706f, 8.75f, 0.875f, 8.75f)
                curveTo(0.64294f, 8.75f, 0.42038f, 8.65781f, 0.25628f, 8.49372f)
                curveTo(0.09219f, 8.32962f, 0.0f, 8.10706f, 0.0f, 7.875f)
                close()
                moveTo(0.0f, 4.375f)
                curveTo(0.0f, 4.14294f, 0.09219f, 3.92038f, 0.25628f, 3.75628f)
                curveTo(0.42038f, 3.59219f, 0.64294f, 3.5f, 0.875f, 3.5f)
                curveTo(1.10706f, 3.5f, 1.32962f, 3.59219f, 1.49372f, 3.75628f)
                curveTo(1.65781f, 3.92038f, 1.75f, 4.14294f, 1.75f, 4.375f)
                curveTo(1.75f, 4.60706f, 1.65781f, 4.82962f, 1.49372f, 4.99372f)
                curveTo(1.32962f, 5.15781f, 1.10706f, 5.25f, 0.875f, 5.25f)
                curveTo(0.64294f, 5.25f, 0.42038f, 5.15781f, 0.25628f, 4.99372f)
                curveTo(0.09219f, 4.82962f, 0.0f, 4.60706f, 0.0f, 4.375f)
                close()
                moveTo(0.0f, 0.875f)
                curveTo(0.0f, 0.64294f, 0.09219f, 0.42038f, 0.25628f, 0.25628f)
                curveTo(0.42038f, 0.09219f, 0.64294f, 0.0f, 0.875f, 0.0f)
                curveTo(1.10706f, 0.0f, 1.32962f, 0.09219f, 1.49372f, 0.25628f)
                curveTo(1.65781f, 0.42038f, 1.75f, 0.64294f, 1.75f, 0.875f)
                curveTo(1.75f, 1.10706f, 1.65781f, 1.32962f, 1.49372f, 1.49372f)
                curveTo(1.32962f, 1.65781f, 1.10706f, 1.75f, 0.875f, 1.75f)
                curveTo(0.64294f, 1.75f, 0.42038f, 1.65781f, 0.25628f, 1.49372f)
                curveTo(0.09219f, 1.32962f, 0.0f, 1.10706f, 0.0f, 0.875f)
                close()
            }
        }
        .build()
        return _more!!
    }

private var _more: ImageVector? = null
