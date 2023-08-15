package com.ssafy.hifes.ui.proof

import NavigationItem
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.ssafy.hifes.data.model.ProofResponseType

private const val TAG = "StampProofScreen"

@Composable
fun ProofScreen(navController: NavController, viewModel: ProofViewModel, type: String, id: Int) {
    var stampResponse = viewModel.stampProofResponse.observeAsState()
    var festivalResponse = viewModel.festivalProofResponse.observeAsState()
    Log.d(TAG, "StampProofScreen: id ${id}")
    Log.d(TAG, "ProofScreen: type ${type}")

    if (type == "stamp") {
        LaunchedEffect(Unit, { viewModel.requestStampProof(id) })

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (stampResponse.value == ProofResponseType.SUCESS) {
                Text(text = "스탬프 도장이 찍혔습니다!")
                TextButton(onClick = {
                    navController.navigate(NavigationItem.Home.screenRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "홈으로")
                }
            } else if (stampResponse.value == ProofResponseType.FAIL) {
                Text(text = "오류로 도장이 찍히지 않았습니다!\n 다시 시도해주세요!")
            } else if (stampResponse.value == ProofResponseType.LOADING) {
                Text(text = "잠시만 기다려주세요")
            }

        }
    } else {

        LaunchedEffect(Unit, { viewModel.requestFestivalProof(id) })
        if (festivalResponse.value != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (festivalResponse.value!!.first == ProofResponseType.SUCESS) {
                    if (festivalResponse.value!!.second == true) {
                        Text(text = "행사 인증이 완료되었습니다!")
                    } else {
                        Text(text = "이미 인증된 행사 입니다!")
                    }
                    TextButton(onClick = {
                        navController.navigate(NavigationItem.Home.screenRoute) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    }) {
                        Text(text = "홈으로")
                    }
                } else if (festivalResponse.value!!.first == ProofResponseType.FAIL) {
                    Text(text = "오류로 인증에 실패했습니다!\n 다시 시도해주세요!")
                } else if (festivalResponse.value!!.first == ProofResponseType.LOADING) {
                    Text(text = "잠시만 기다려주세요")
                }

            }
        }

    }

}