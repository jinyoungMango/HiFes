package myiconpack


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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.hifes.ui.iconpack.MyIconPack

public val MyIconPack.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user = Builder(name = "User", defaultWidth = 50.0.dp, defaultHeight = 50.0.dp,
                viewportWidth = 50.0f, viewportHeight = 50.0f).apply {
            path(fill = SolidColor(Color(0xFFEEEEEE)), stroke = null, fillAlpha = 0.933333f,
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(5.0f, 0.0f)
                lineTo(45.0f, 0.0f)
                arcTo(5.0f, 5.0f, 0.0f, false, true, 50.0f, 5.0f)
                lineTo(50.0f, 45.0f)
                arcTo(5.0f, 5.0f, 0.0f, false, true, 45.0f, 50.0f)
                lineTo(5.0f, 50.0f)
                arcTo(5.0f, 5.0f, 0.0f, false, true, 0.0f, 45.0f)
                lineTo(0.0f, 5.0f)
                arcTo(5.0f, 5.0f, 0.0f, false, true, 5.0f, 0.0f)
                close()
            }
            group {
                path(fill = SolidColor(Color(0xFF969696)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(25.0f, 24.9721f)
                    curveTo(23.1667f, 24.9721f, 21.6435f, 24.3656f, 20.4306f, 23.1527f)
                    curveTo(19.2176f, 21.9397f, 18.6111f, 20.4165f, 18.6111f, 18.5832f)
                    curveTo(18.6111f, 16.7499f, 19.2176f, 15.2267f, 20.4306f, 14.0138f)
                    curveTo(21.6435f, 12.8008f, 23.1667f, 12.1943f, 25.0f, 12.1943f)
                    curveTo(26.8334f, 12.1943f, 28.3565f, 12.8008f, 29.5695f, 14.0138f)
                    curveTo(30.7824f, 15.2267f, 31.3889f, 16.7499f, 31.3889f, 18.5832f)
                    curveTo(31.3889f, 20.4165f, 30.7824f, 21.9397f, 29.5695f, 23.1527f)
                    curveTo(28.3565f, 24.3656f, 26.8334f, 24.9721f, 25.0f, 24.9721f)
                    close()
                    moveTo(11.6667f, 38.3332f)
                    verticalLineTo(34.1665f)
                    curveTo(11.6667f, 33.148f, 11.9236f, 32.2569f, 12.4375f, 31.493f)
                    curveTo(12.9514f, 30.7291f, 13.6204f, 30.148f, 14.4445f, 29.7499f)
                    curveTo(16.2593f, 28.9073f, 18.0324f, 28.2754f, 19.7639f, 27.854f)
                    curveTo(21.4954f, 27.4328f, 23.2408f, 27.2221f, 25.0f, 27.2221f)
                    curveTo(26.7593f, 27.2221f, 28.5f, 27.4374f, 30.2222f, 27.868f)
                    curveTo(31.9445f, 28.2985f, 33.7122f, 28.9276f, 35.5256f, 29.7553f)
                    curveTo(36.3738f, 30.1558f, 37.0538f, 30.7365f, 37.5656f, 31.4974f)
                    curveTo(38.0774f, 32.2583f, 38.3334f, 33.148f, 38.3334f, 34.1665f)
                    verticalLineTo(38.3332f)
                    horizontalLineTo(11.6667f)
                    close()
                    moveTo(14.4444f, 35.5555f)
                    horizontalLineTo(35.5556f)
                    verticalLineTo(34.1665f)
                    curveTo(35.5556f, 33.7684f, 35.4422f, 33.3934f, 35.2153f, 33.0415f)
                    curveTo(34.9885f, 32.6897f, 34.7037f, 32.4258f, 34.3611f, 32.2499f)
                    curveTo(32.676f, 31.4258f, 31.088f, 30.8448f, 29.5972f, 30.5068f)
                    curveTo(28.1065f, 30.1689f, 26.5741f, 29.9999f, 25.0f, 29.9999f)
                    curveTo(23.4259f, 29.9999f, 21.8843f, 30.1689f, 20.375f, 30.5068f)
                    curveTo(18.8658f, 30.8448f, 17.2778f, 31.4258f, 15.6111f, 32.2499f)
                    curveTo(15.2685f, 32.4258f, 14.9884f, 32.6897f, 14.7709f, 33.0415f)
                    curveTo(14.5532f, 33.3934f, 14.4444f, 33.7684f, 14.4444f, 34.1665f)
                    verticalLineTo(35.5555f)
                    close()
                    moveTo(25.0f, 22.1943f)
                    curveTo(26.0278f, 22.1943f, 26.8866f, 21.8494f, 27.5764f, 21.1596f)
                    curveTo(28.2662f, 20.4698f, 28.6111f, 19.611f, 28.6111f, 18.5832f)
                    curveTo(28.6111f, 17.5554f, 28.2662f, 16.6966f, 27.5764f, 16.0068f)
                    curveTo(26.8866f, 15.317f, 26.0278f, 14.9721f, 25.0f, 14.9721f)
                    curveTo(23.9722f, 14.9721f, 23.1135f, 15.317f, 22.4236f, 16.0068f)
                    curveTo(21.7338f, 16.6966f, 21.3889f, 17.5554f, 21.3889f, 18.5832f)
                    curveTo(21.3889f, 19.611f, 21.7338f, 20.4698f, 22.4236f, 21.1596f)
                    curveTo(23.1135f, 21.8494f, 23.9722f, 22.1943f, 25.0f, 22.1943f)
                    close()
                }
            }
        }
        .build()
        return _user!!
    }

private var _user: ImageVector? = null
