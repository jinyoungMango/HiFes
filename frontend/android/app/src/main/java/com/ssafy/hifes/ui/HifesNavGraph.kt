package com.ssafy.hifes.ui

import NavigationItem
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.ssafy.hifes.data.AppContainer
import com.ssafy.hifes.ui.board.BoardScreen
import com.ssafy.hifes.ui.board.BoardViewModel
import com.ssafy.hifes.ui.board.boarddetail.BoardDetailScreen
import com.ssafy.hifes.ui.board.write.PostWriteScreen
import com.ssafy.hifes.ui.detail.DetailViewModel
import com.ssafy.hifes.ui.detail.FestivalDetail
import com.ssafy.hifes.ui.group.GroupViewModel
import com.ssafy.hifes.ui.group.create.GroupCreateScreen
import com.ssafy.hifes.ui.group.info.GroupInfoScreen
import com.ssafy.hifes.ui.group.info.chat.ChatViewModel
import com.ssafy.hifes.ui.group.main.GroupMainScreen
import com.ssafy.hifes.ui.home.HomeScreen
import com.ssafy.hifes.ui.home.search.HomeFestivalSearchScreen
import com.ssafy.hifes.ui.login.LoginDetailScreen
import com.ssafy.hifes.ui.login.LoginScreen
import com.ssafy.hifes.ui.login.LoginViewModel
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.map.MapScreen
import com.ssafy.hifes.ui.mypage.main.MyPageScreen
import com.ssafy.hifes.ui.mypage.participatedfest.ParticipatedFestScreen
import com.ssafy.hifes.ui.mypage.MyPageViewModel
import com.ssafy.hifes.ui.proof.ProofScreen
import com.ssafy.hifes.ui.proof.ProofViewModel

private const val TAG = "HifesNavGraph"

@Composable
fun HifesNavGraph(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel,
    startDestination: String = HifesDestinations.LOGIN_ROUTE
//    startDestination: String = NavigationItem.Home.screenRoute
) {
    val uri = "hifes://main"

    val boardViewModel: BoardViewModel = hiltViewModel()
    val groupViewModel: GroupViewModel = hiltViewModel()
    val detailViewModel: DetailViewModel = hiltViewModel()
    val proofViewModel: ProofViewModel = hiltViewModel()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val chatViewModel: ChatViewModel = hiltViewModel()
    val participatedFestViewModel: MyPageViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavigationItem.Home.screenRoute) {
            HomeScreen(navController, mainViewModel)
        }
        composable(NavigationItem.Map.screenRoute) {
            MapScreen(navController, mainViewModel, detailViewModel)
        }
        composable(NavigationItem.Group.screenRoute) {
            GroupMainScreen(navController, groupViewModel, mainViewModel)
        }
        composable(
            route = HifesDestinations.LOGIN_ROUTE
        ) {
            LoginScreen(navController = navController, loginViewModel)
        }
        composable(
            route = HifesDestinations.LOGIN_DETAIL_ROUTE
        ) {
            LoginDetailScreen(navController = navController, loginViewModel)
        }
        composable(
            route = HifesDestinations.FESTIVAL_DETAIL
        ) {
            FestivalDetail(
                navController = navController,
                viewModel = mainViewModel,
                detailViewModel = detailViewModel
            )
        }
        composable(
            route = HifesDestinations.PARTICIPATED_FEST_ROUTE
        ) {
            ParticipatedFestScreen(navController = navController, participatedFestViewModel)
        }
        composable(
            route = HifesDestinations.MY_PAGE_ROUTE
        ) {
            MyPageScreen(navController = navController)
        }
        composable(
            route = HifesDestinations.BOARD_ROUTE
        )
        {
            BoardScreen(navController = navController, viewModel = boardViewModel)
        }
        composable(
            route = HifesDestinations.BOARD_DETAIL_ROUTE
        ) {
            BoardDetailScreen(navController = navController, viewModel = boardViewModel)
        }
        composable(
            route = HifesDestinations.GROUP_CREATE
        ) {
            GroupCreateScreen(
                navController = navController,
                viewModel = groupViewModel,
                mainViewModel = mainViewModel
            )
        }
        composable(
            route = HifesDestinations.POST_WRITE_ROUTE
        ) {
            PostWriteScreen(navController = navController, viewModel = boardViewModel)
        }
        composable(
            route = HifesDestinations.GROUP_DETAIL
        ) {
            GroupInfoScreen(
                navController = navController,
                groupViewModel = groupViewModel,
                chatViewModel = chatViewModel
            )
        }
        composable(
            route = HifesDestinations.HOME_SEARCH
        ) {
            HomeFestivalSearchScreen(navController = navController, mainViewModel)
        }
        composable(
            route = HifesDestinations.STAMP_PROOF,
            deepLinks = listOf(navDeepLink {
                uriPattern = "hifes://main?type={type}&id={id}"
                action = Intent.ACTION_VIEW
            })
        ) {
            val type = it.arguments?.getString("type")
            val id = it.arguments?.getString("id")
            Log.d(TAG, "HifesNavGraph: id ${id}")
            if (type != null && id != null && (type == "festival" || type == "stamp")) {
                var isNumber = false
                var idNumber = -1
                try {
                    idNumber = id.toInt()
                    isNumber = true
                } catch (e: Exception) {
                    isNumber = false
                }

                if (isNumber == true) {
                    ProofScreen(
                        navController = navController,
                        viewModel = proofViewModel,
                        type = type,
                        id = idNumber
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "잘못된 QR입니다")
                    }
                }


            } else
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "잘못된 QR입니다")
                }

        }
    }
}
