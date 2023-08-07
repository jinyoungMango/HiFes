package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.ssafy.hifes.R
import com.ssafy.hifes.data.model.MarkerDto
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.common.ChipsSelectable
import com.ssafy.hifes.ui.detail.DetailViewModel
import com.ssafy.hifes.ui.detail.map.MarkerDetailDialog
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private const val TAG = "MapScreen_하이페스"

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MainViewModel,
    detailViewModel: DetailViewModel
) {
    val festivalList = viewModel.festivalList.observeAsState()
    val mapType = viewModel.mapType.observeAsState()
    val boothList = detailViewModel.markerList.observeAsState()
    val selectedBoothChip = detailViewModel.selectedBoothChip.observeAsState()

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
                    when (mapType.value) {
                        MapType.GENERAL -> {
                            AroundMyLocationFestivalMap(
                                festivalList.value,
                                sheetState,
                                coroutineScope
                            )
                            if (!festivalList.value.isNullOrEmpty()) {
                                ViewPager(
                                    festivalList.value!!,
                                    modifier = Modifier.align(Alignment.BottomCenter)
                                )
                            }
                        }

                        MapType.FESTIVAL -> {
                            BoothMap(boothList.value!!, selectedBoothChip.value ?: 0)
                            ChipsSelectable(
                                listOf(
                                    R.string.map_chip_total,
                                    R.string.map_chip_sale_booth,
                                    R.string.map_chip_food_booth,
                                    R.string.map_chip_restaurant_booth,
                                    R.string.map_chip_staff,
                                    R.string.map_chip_safety_staff,
                                    R.string.map_chip_toilet,
                                    R.string.map_chip_enterance
                                ).map { stringResource(id = it) }
                            ) { index ->
                                detailViewModel.updateSelectedBoothChip(index)
                            }
                        }

                        else -> {}
                    }
                }

            }
        },
        floatingActionButton = {
            // 현재 사용자가 그룹이 있는지 없는지 판단하에 보여주기
            if (mapType.value == MapType.FESTIVAL) {
                FloatingActionButton(
                    onClick = { /* 모임콜 기능 */ },
                    containerColor = PrimaryPink,
                    contentColor = Color.White,
                    shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
                ) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Group Call",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterialApi::class)
@Composable
fun AroundMyLocationFestivalMap(
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

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun BoothMap(
    boothList: MutableList<MarkerDto>,
    selectedBoothChip: Int,
) {

    var markers by remember {
        mutableStateOf<List<Marker>>(emptyList())
    }
    var latAvg = 0.0
    var lngAvg = 0.0
    var boothAvgLatLng by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(boothList) {
        markers.forEach { it.map = null }
        markers = boothList.map {
            latAvg += it.boothLatitude
            lngAvg += it.boothLongitude

            Marker().apply {
                position = LatLng(it.boothLatitude, it.boothLongitude)
                subCaptionText = it.boothNo.toString()
                icon = OverlayImage.fromResource(setMarkerIcon(it.boothNo))
            }
        }

        latAvg /= boothList.size
        lngAvg /= boothList.size
        Log.d(TAG, "BoothMap: $latAvg, $lngAvg")

        boothAvgLatLng = LatLng(latAvg, lngAvg)
        cameraPositionState.position = CameraPosition(boothAvgLatLng, 14.0)
    }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        MarkerDetailDialog(onDismissRequest = { showDialog = false })
    }

    NaverMap(
        cameraPositionState = cameraPositionState
    ) {

        markers.forEach { marker ->
            if (selectedBoothChip == 0) {
                Marker(
                    state = MarkerState(position = marker.position),
                    captionText = marker.captionText,
                    icon = marker.icon,
                    onClick = {
                        showDialog = true
                        true
                    }
                )
            } else if (marker.subCaptionText == selectedBoothChip.toString()) {
                Marker(
                    state = MarkerState(position = marker.position),
                    captionText = marker.captionText,
                    icon = marker.icon,
                    onClick = {
                        showDialog = true
                        true
                    }
                )
            }

        }
    }
}


fun setMarkerIcon(boothNo: Int): Int {
    var markerImg = 0
    when (boothNo) {
        1 -> markerImg = R.drawable.marker_sale_booth
        2 -> markerImg = R.drawable.marker_food_booth
        3 -> markerImg = R.drawable.marker_restaurant_booth
        4 -> markerImg = R.drawable.marker_staff
        5 -> markerImg = R.drawable.marker_safety_staff
        6 -> markerImg = R.drawable.marker_toilet
        7 -> markerImg = R.drawable.marker_enterance
    }
    return markerImg
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapScreenPriview() {
    MapScreen(rememberNavController(), MainViewModel(), DetailViewModel())
}