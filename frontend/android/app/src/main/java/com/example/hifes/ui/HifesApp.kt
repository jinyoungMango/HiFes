package com.example.hifes.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hifes.data.AppContainer
import com.example.hifes.ui.theme.HifesTheme

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
        HifesNavGraph(
            appContainer = appContainer,
            navController = navController
        )
    }
}