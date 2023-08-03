package com.ssafy.hifes.ui

import BottomNavigation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.data.AppContainer
import com.ssafy.hifes.ui.HifesNavGraph
import com.ssafy.hifes.ui.theme.HifesTheme

@Composable
fun HifesApp(
    appContainer: AppContainer
) {
    HifesTheme {
        val navController = rememberNavController()

        val coroutineScope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: HifesDestinations.LOGIN_ROUTE
        var isBottomBarVisible = true
        navBackStackEntry?.destination?.route?.let { route ->
            isBottomBarVisible = when(route) {
                NavigationItem.Home.screenRoute -> true
                NavigationItem.Map.screenRoute -> true
                NavigationItem.Group.screenRoute -> true
                else -> false
            }
        }
        Scaffold(
            bottomBar = {
                if (isBottomBarVisible) BottomNavigation(navController = navController) }
        ) {
            Box(Modifier.padding(it)){
                HifesNavGraph(
                    appContainer = appContainer,
                    navController = navController
                )
            }
        }
    }
}