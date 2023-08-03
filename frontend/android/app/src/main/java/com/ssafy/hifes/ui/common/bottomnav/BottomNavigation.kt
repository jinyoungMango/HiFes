import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.common.bottomnav.GROUP
import com.ssafy.hifes.ui.common.bottomnav.HOME
import com.ssafy.hifes.ui.common.bottomnav.MAP
import com.ssafy.hifes.ui.theme.PrimaryPink


@Preview
@Composable
fun HomePrev() {
    val navController = rememberNavController()
    BottomNavigation(navController = navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<NavigationItem>(
        NavigationItem.Home,
        NavigationItem.Map,
        NavigationItem.Group
    )


    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(29.dp)
                            .height(29.dp)
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 10.sp) },
                selectedContentColor = PrimaryPink,
                unselectedContentColor = Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


sealed class NavigationItem(val title: Int, val icon: Int, val screenRoute: String) {
    object Home : NavigationItem(R.string.nav_home, R.drawable.icon_home, HOME)
    object Map : NavigationItem(R.string.nav_map, R.drawable.icon_map, MAP)
    object Group : NavigationItem(R.string.nav_group, R.drawable.icon_group, GROUP)
}

