package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssafy.hifes.R
import com.ssafy.hifes.ui.detail.ComposeMapView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint


private const val TAG = "MapScreen_하이페스"
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
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
                MapViewWithMarker(36.105994, 128.425637, sheetState, coroutineScope)
                ViewPager(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }

    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapViewWithMarker(
    latitude: Double,
    longitude: Double,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    val mapViewState = ComposeMapView(latitude, longitude)
    val mapView = mapViewState.value

    mapView?.let { map ->
        LaunchedEffect(key1 = map) {

            map.setPOIItemEventListener(MarkerEventListener())

            val marker = MapPOIItem().apply {
                itemName = "MARKER_NAME"
                tag = 0
                markerType = MapPOIItem.MarkerType.CustomImage
                customImageResourceId = R.drawable.icon_marker
//                selectedMarkerType = MapPOIItem.MarkerType.CustomImage
                isCustomImageAutoscale = false
                mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            }
            map.addPOIItem(marker)
//            map.selectPOIItem(marker, false)
        }

        AndroidView(
            factory = { map },
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MapScreenPriview() {
    MapScreen(navController = rememberNavController())
}