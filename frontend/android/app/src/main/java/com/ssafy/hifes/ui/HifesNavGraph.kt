package com.ssafy.hifes.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.data.AppContainer
import com.ssafy.hifes.ui.detail.FestivalDetail
import com.ssafy.hifes.ui.login.LoginDetailScreen
import com.ssafy.hifes.ui.login.LoginScreen
import com.ssafy.hifes.ui.map.MapScreen
import com.ssafy.hifes.ui.mypage.MyPageScreen
import com.ssafy.hifes.ui.board.BoardScreen
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boarddetail.BoardDetailScreen
import com.ssafy.hifes.ui.map.MapViewModel
import com.ssafy.hifes.ui.participatedfest.ParticipatedFestScreen


@Composable
fun HifesNavGraph(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HifesDestinations.FESTIVAL_DETAIL
) {

    val boardViewModel: BoardViewModel = viewModel()
    val mapViewModel : MapViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = HifesDestinations.LOGIN_ROUTE
        ) { navBackStackEntry ->
            LoginScreen(navController = navController)
        }
        composable(
            route = HifesDestinations.LOGIN_DETAIL_ROUTE
        ) { navBackStackEntry ->
            LoginDetailScreen(navController = navController)
        }
        composable(
            route = HifesDestinations.FESTIVAL_DETAIL
        ) { navBackStackEntry ->
            FestivalDetail(navController = navController)
        }
        composable(
            route = HifesDestinations.PARTICIPATED_FEST_ROUTE
        ) { navBackStackEntry ->
            ParticipatedFestScreen(navController = navController)
        }
        composable(
            route = HifesDestinations.MY_PAGE_ROUTE
        ) { navBackStackEntry ->
            MyPageScreen(navController = navController)
        }
        composable(
            route = HifesDestinations.MAP
        ) { navBackStackEntry ->
            MapScreen(navController = navController, viewModel = mapViewModel)

        }
        composable(
            route = HifesDestinations.BOARD_ROUTE
        )
        { navBackStackEntry ->
            BoardScreen(navController = navController, viewModel = boardViewModel)
        }
        composable(
            route = HifesDestinations.BOARD_DETAIL_ROUTE
        ) { navBackStackEntry ->
            BoardDetailScreen(navController = navController, viewModel = boardViewModel)
        }
    }
}
