import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hifes.R


@Preview
@Composable
fun HomePrev() {
    NavigationBar()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar() {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.Profile
    )
    var selectedItem by remember { mutableStateOf<NavigationItem>(NavigationItem.Home) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier.graphicsLayer {
                    shadowElevation = 20f
                }
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            val tint =
                                if (item == selectedItem) colorResource(R.color.main) else Color.Gray
                            Image(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                modifier = Modifier.size(30.dp),
                                colorFilter = ColorFilter.tint(tint)
                            )
                        },
                        label = {
                            val textColor =
                                if (item == selectedItem) colorResource(R.color.main) else Color.Gray
                            Text(item.title, color = textColor)
                        },
                        selected = selectedItem == item,
                        onClick = { selectedItem = item },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.White
                        )
                    )
                }
            }

        }
    ) {
        // Your screen content here
    }


}


sealed class NavigationItem(val title: String, @DrawableRes val icon: Int) {
    object Home : NavigationItem("홈", R.drawable.icon_home)
    object Favorites : NavigationItem("지도", R.drawable.icon_map)
    object Profile : NavigationItem("모임", R.drawable.icon_group)
}

