package com.ssafy.hifes.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.ui.login.LoginScreen
import com.ssafy.hifes.ui.login.LoginDetailScreen
import com.ssafy.hifes.data.AppContainer
import com.ssafy.hifes.ui.detail.FestivalDetail
import com.ssafy.hifes.ui.map.MapBottomSheet
import com.ssafy.hifes.ui.map.MapScreen

@Composable
fun HifesNavGraph(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HifesDestinations.MAP
    ) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(
            route = HifesDestinations.LOGIN_ROUTE
        ){ navBackStackEntry ->
            LoginScreen(navController = navController)
        }
        composable(
            route = HifesDestinations.LOGIN_DETAIL_ROUTE
        ){ navBackStackEntry ->
            LoginDetailScreen(navController = navController)
        }
        composable(
            route = HifesDestinations.FESTIVAL_DETAIL
        ){ navBackStackEntry ->
            FestivalDetail(navController = navController)
        }
        composable(
            route = HifesDestinations.MAP
        ){ navBackStackEntry ->
            MapScreen(navController = navController)
        }
    }
}
