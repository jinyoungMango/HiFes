package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import kotlinx.coroutines.launch


private const val TAG = "MapScreen_하이페스"

@OptIn(ExperimentalMaterialApi::class, ExperimentalNaverMapApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { MapScreenContent() },
        modifier = Modifier.fillMaxSize()
    ) {

    }
    Scaffold(
        topBar = { MapAppBar() },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NaverMap(
                    modifier = Modifier.fillMaxSize()
                )
//                NaverMapTest()
                ViewPager(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }

    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapTest() {
    val seoul = LatLng(37.532600, 127.024612)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        // 카메라 초기 위치를 설정합니다.
        position = CameraPosition(seoul, 11.0)
    }
    Box(Modifier.fillMaxSize()) {
        NaverMap(cameraPositionState = cameraPositionState)
        Button(onClick = {
            // 카메라를 새로운 줌 레벨로 이동합니다.
            cameraPositionState.move(CameraUpdate.zoomIn())
        }) {
            Text(text = "Zoom In")
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapScreenPriview() {
//    MapScreen(navController = rememberNavController())
    NaverMap(
        modifier = Modifier.fillMaxSize()
    )
}