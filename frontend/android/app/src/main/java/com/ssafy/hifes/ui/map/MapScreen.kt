package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.common.ChipsSelectable
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private const val TAG = "MapScreen_하이페스"

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen(navController: NavController, viewModel: MainViewModel) {
    val festivalList = viewModel.festivalList.observeAsState()
    val mapType = viewModel.mapType.observeAsState()

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    Scaffold(
        content = {
            ModalBottomSheetLayout(
                sheetState = sheetState,
                sheetContent = { DialogContent(festivalList.value!![0], 4.0) },
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.0f))
                ) {
                    Log.d(TAG, "MapScreen: ${mapType.value}")
                    if (mapType.value == MapType.GENERAL) {
                        Log.d(TAG, "MapScreen: ")
                    }
                    AroundMyLocationFestival(festivalList.value, sheetState, coroutineScope)
                    if (!festivalList.value.isNullOrEmpty() && mapType.value == MapType.GENERAL) {
                        ViewPager(
                            festivalList.value!!,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }

                    if (mapType.value == MapType.FESTIVAL) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            ChipsSelectable(
                                listOf(
                                    stringResource(id = R.string.board_chip_notification),
                                    stringResource(id = R.string.board_chip_ask),
                                    stringResource(id = R.string.board_chip_free),
                                    stringResource(id = R.string.board_chip_review)
                                )
                            ) { index ->
                                when (index) {

                                }
                            }
                        }
                    }
                }

            }
        },
        floatingActionButton = {
            if (mapType.value == MapType.FESTIVAL) {
                FloatingActionButton(
                    onClick = { navController.navigate(HifesDestinations.GROUP_CREATE) },
                    containerColor = PrimaryPink,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }

        }
    )


}

@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterialApi::class)
@Composable
fun AroundMyLocationFestival(
    festivalList: MutableList<OrganizedFestivalDto>?,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    NaverMap(
        locationSource = rememberFusedLocationSource(),
        properties = MapProperties(
            locationTrackingMode = LocationTrackingMode.Follow,
        ),
        uiSettings = MapUiSettings(
            isLocationButtonEnabled = true,
        )

    ) {
        var markers by remember {
            mutableStateOf<List<Marker>>(emptyList())
        }

        LaunchedEffect(festivalList) {
            markers.forEach { it.map = null }
            if (festivalList != null) {
                markers = festivalList.map {
                    Marker().apply {
                        position = LatLng(it.fesLatitude, it.fesLongitude)
                        captionText = it.fesTitle
                        icon = OverlayImage.fromResource(R.drawable.icon_marker)
                    }
                }
            }
        }
        markers.forEach {
            Marker(
                state = MarkerState(position = it.position),
                captionText = it.captionText,
                icon = it.icon,
                onClick = {
                    Log.d(TAG, "AroundMyLocationFestival: 클릭")
                    coroutineScope.launch {
                        sheetState.show()
                    }
                    true
                }
            )
        }

    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapScreenPriview() {
    val mainViewModel: MainViewModel = viewModel()
    MapScreen(rememberNavController(), mainViewModel)
}