package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapEffect
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
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.CustomMarker
import com.ssafy.hifes.data.model.MarkerDto
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.HifesDestinations
import com.ssafy.hifes.ui.animations.WavesAnimation
import com.ssafy.hifes.ui.common.ChipsSelectable
import com.ssafy.hifes.ui.detail.DetailViewModel
import com.ssafy.hifes.ui.detail.map.MarkerDetailDialog
import com.ssafy.hifes.ui.main.MainViewModel
import com.ssafy.hifes.ui.theme.PrimaryPink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
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
    val festivalInfo by viewModel.festivalInfo.observeAsState()
    detailViewModel.updateSelectedBoothChip(0)

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    var selectedFestival by remember { mutableStateOf<OrganizedFestivalDto?>(null) }

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }
    var location = viewModel.location.observeAsState()
    var showWavesAnimation by remember { mutableStateOf(false) }
    var fabVisible by remember { mutableStateOf(true) }
    var fabClicked by remember { mutableStateOf(false) }

    var groupCallLat = remember { mutableStateOf(0.0) }
    var groupCallLng = remember { mutableStateOf(0.0) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (fabClicked) {
        Toast.makeText(context, "지도를 움직여 위치를 설정하세요.", Toast.LENGTH_SHORT).show()
    }
    if (showDialog) {
        MapGroupCallDialog(
            viewModel.festivalInfo.value!!,
            detailViewModel,
            groupCallLat.value,
            groupCallLng.value
        ) { isConfirm ->
            if (isConfirm) { // 모임콜 확인 버튼 눌렀을 경우
                showWavesAnimation = true
                fabVisible = false  // FAB를 숨깁니다.
                coroutineScope.launch {
                    delay(2100L)
                    fabVisible = true  // FAB를 다시 보이게 합니다.
                }
            }
            showDialog = false

        }
    }

    Scaffold(
        content = {
            ModalBottomSheetLayout(
                sheetState = sheetState,
                sheetContent = {
                    selectedFestival?.let { festival ->
                        // 추후 서버에서 가져온 score로 변경
                        DialogContent(festival) {
                            viewModel.getFestivalInfo(festival.festivalId)
                            navController.navigate(HifesDestinations.FESTIVAL_DETAIL)
                        }
                    }
                },
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
                            ) { selectedFestival = it }
                            if (!festivalList.value.isNullOrEmpty()) {
                                ViewPager(
                                    navController = navController,
                                    festivalList = festivalList.value!!,
                                    modifier = Modifier.align(Alignment.BottomCenter),
                                    viewModel = viewModel
                                )
                            }
                        }

                        MapType.FESTIVAL -> {
                            boothList.value?.let { booth ->
                                BoothMap(
                                    booth,
                                    selectedBoothChip.value ?: 0,
                                    location.value,
                                    fabClicked,
                                    festivalInfo!!.festivalId
                                ) { lat, lng ->
                                    groupCallLat.value = lat
                                    groupCallLng.value = lng

                                }
                            }
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
            if (mapType.value == MapType.FESTIVAL && viewModel.festivalInfo.value?.isUserJoined == true) {
                if (fabVisible) {
                    FloatingActionButton(
                        onClick = { /* 모임콜 기능 */
                            if (!fabClicked) {

                            } else {
                                showDialog = true

                            }
                            fabClicked = !fabClicked
                        },
                        containerColor = PrimaryPink,
                        contentColor = Color.White,
                        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
                    ) {
                        if (!fabClicked) {
                            Icon(
                                Icons.Filled.Notifications,
                                contentDescription = "Group Call",
                                modifier = Modifier.size(28.dp)
                            )
                        } else { // 모임콜 버튼 눌린 상태일 때
                            Icon(
                                Icons.Filled.Check,
                                contentDescription = "Group Call",
                                modifier = Modifier.size(28.dp)
                            )
                        }

                    }
                }

                if (showWavesAnimation) {
                    WavesAnimationCentered { showWavesAnimation = false }
                }
            }
        }

    )
}

@Composable
fun WavesAnimationCentered(onAnimationEnd: () -> Unit) {
    WavesAnimation()
    LaunchedEffect(key1 = true) {
        delay(2000L) // 2 seconds
        onAnimationEnd()
    }

}


@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterialApi::class)
@Composable
fun AroundMyLocationFestivalMap(
    festivalList: List<OrganizedFestivalDto>?,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    onFestivalSelected: (OrganizedFestivalDto) -> Unit
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
        markers.forEachIndexed { index, marker ->
            Marker(
                state = MarkerState(position = marker.position),
                captionText = marker.captionText,
                icon = marker.icon
            ) {
                Log.d(TAG, "AroundMyLocationFestival: 클릭")
                coroutineScope.launch {
                    onFestivalSelected(festivalList!![index])
                    sheetState.show()
                }
                true
            }
        }

    }

}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun BoothMap(
    boothList: List<MarkerDto>,
    selectedBoothChip: Int,
    location: Location?,
    fabClicked: Boolean,
    festivalId: Int,
    callLatLng: (lat: Double, lng: Double) -> Unit
) {
    var markers by remember {
        mutableStateOf<List<CustomMarker>>(emptyList())
    }
    var currLatLng by remember {
        mutableStateOf(location?.let {
            LatLng(
                it.latitude,
                it.longitude
            )
        })
    }
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(boothList) {
        markers.forEach { it.marker.map = null }
        markers = boothList.map {
            CustomMarker(
                marker = Marker().apply {
                    position = LatLng(it.boothLatitude, it.boothLongitude)
                    subCaptionText = it.boothNo.toString()
                    icon = OverlayImage.fromResource(setMarkerIcon(it.boothNo))
                },
                boothName = it.boothName,
                description = it.description
            )
        }
        cameraPositionState.position = currLatLng?.let { CameraPosition(it, 14.0) }!!
    }

    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    if (showDialog) {
        MarkerDetailDialog(title, content, onDismissRequest = { showDialog = false })
    }

    var groupCallLatLng = rememberCameraPositionState()
    NaverMap(
        locationSource = rememberFusedLocationSource(),
        properties = MapProperties(
            locationTrackingMode = LocationTrackingMode.Follow,
        ),
        uiSettings = MapUiSettings(
            isLocationButtonEnabled = true,
        ),
        cameraPositionState = cameraPositionState,
    ) {
        // sharedPreferences에 모임콜 정보가 저장된 경우
//        BlinkingMarker(festivalId)

        val groupCallLocation = AppPreferences.getCallLocation()
        val groupCallFestivalId = groupCallLocation.first
//        var callLatLng = remember {
//            mutableStateOf(LatLng(0.0, 0.0))
//        }
        if (groupCallFestivalId == festivalId.toString()) {
            Log.d(TAG, "BlinkingMarker: 아이디 확인")

            var callLatLng =
                LatLng(
                    groupCallLocation.second[0].toDouble(),
                    groupCallLocation.second[1].toDouble()
                )
            Log.d(TAG, "BoothMap: $callLatLng")

            val infiniteTransition = rememberInfiniteTransition()
            val alpha by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 0.1f,
                animationSpec = infiniteRepeatable(
                    tween(500, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )

            // 마커의 표시 여부를 제어하는 상태를 추가
            var showMarker by remember { mutableStateOf(true) }
            var showGroupCallDialog by remember { mutableStateOf(false) }
            if (showGroupCallDialog) {
                GroupCallDialog {
                    if (it) {
                        showMarker = false
                        AppPreferences.removeCallLocation()
                    }
                    showGroupCallDialog = !showGroupCallDialog
                }
            }

            if (groupCallFestivalId == festivalId.toString() && showMarker) {
                Log.d(TAG, "BlinkingMarker 모임콜: $festivalId")
                // Assuming the `Marker` composable has an `alpha` parameter or similar. If not, you'll need a different approach.
                Marker(
                    state = MarkerState(position = callLatLng),
                    icon = OverlayImage.fromResource(R.drawable.icon_group_call),
                alpha = alpha
                ) {
                    showGroupCallDialog = !showGroupCallDialog
                    false
                }
            }
        }

        // =====================================
        // 현재 위치를 가져와야 함
        groupCallLatLng.position = cameraPositionState.position
        if (fabClicked) {
            Marker(
                state = MarkerState(position = groupCallLatLng.position.target),
                icon = OverlayImage.fromResource(R.drawable.icon_group_call),
            ) {
                false
            }
            MapEffect(cameraPositionState) { naverMap ->
                naverMap.addOnCameraChangeListener { i, b ->
                    // 현재 보이는 네이버맵의 정중앙 가운데로 마커 이동
                    val callLatLng = LatLng(
                        naverMap.cameraPosition.target.latitude,
                        naverMap.cameraPosition.target.longitude
                    )
                    groupCallLatLng.position = CameraPosition(callLatLng, 13.0)
                }

                naverMap.addOnCameraIdleListener {
                    val callLatLng = LatLng(
                        naverMap.cameraPosition.target.latitude,
                        naverMap.cameraPosition.target.longitude
                    )
                    callLatLng(
                        naverMap.cameraPosition.target.latitude,
                        naverMap.cameraPosition.target.longitude
                    )
                }

            }
        }
        markers.forEach { customMarker ->
            val marker = customMarker.marker
            if (selectedBoothChip == 0) {
                Marker(
                    state = MarkerState(position = marker.position),
                    captionText = marker.captionText,
                    icon = marker.icon,
                    onClick = {
                        title = customMarker.boothName
                        content = customMarker.description
                        showDialog = true
                        true
                    }
                )
            } else if (marker.subCaptionText == selectedBoothChip.toString()) {
                Marker(
                    state = MarkerState(position = marker.position),
                    captionText = marker.captionText,
                    icon = marker.icon
                ) {
                    Log.d(TAG, "BoothMap: ")
                    title = customMarker.boothName
                    content = customMarker.description
                    showDialog = true
                    true
                }
            }
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun BlinkingMarker(festivalId: Int) {
//    Log.d(TAG, "BlinkingMarker: $festivalId")
    val groupCallLocation = AppPreferences.getCallLocation()
    val groupCallFestivalId = groupCallLocation.first
    var callLatLng = LatLng(0.0, 0.0)
    if (groupCallFestivalId == festivalId.toString()) {
        Log.d(TAG, "BlinkingMarker: 아이디 확인")
        callLatLng =
            LatLng(groupCallLocation.second[0].toDouble(), groupCallLocation.second[1].toDouble())
    }

    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.1f,
        animationSpec = infiniteRepeatable(
            tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // 마커의 표시 여부를 제어하는 상태를 추가
    var showMarker by remember { mutableStateOf(true) }
    var showGroupCallDialog by remember { mutableStateOf(false) }
    if (showGroupCallDialog) {
        GroupCallDialog {
            if (it) {
                showMarker = false
                AppPreferences.removeCallLocation()
            }
            showGroupCallDialog = !showGroupCallDialog
        }
    }

    if (groupCallFestivalId == festivalId.toString() && showMarker) {
        Log.d(TAG, "BlinkingMarker 모임콜: $festivalId")
        // Assuming the `Marker` composable has an `alpha` parameter or similar. If not, you'll need a different approach.
        Marker(
            state = MarkerState(position = callLatLng),
            icon = OverlayImage.fromResource(R.drawable.icon_group_call),
            alpha = alpha
        ) {
            showGroupCallDialog = !showGroupCallDialog
            false
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
//    MapScreen(rememberNavController(), MainViewModel(), DetailViewModel())
}