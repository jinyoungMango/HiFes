package com.ssafy.hifes.ui.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ssafy.hifes.ui.detail.ComposeMapViewWithMarker
import com.ssafy.hifes.ui.home.HomeCardList
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(navController: NavController) {
    Scaffold(
        topBar = { MapAppBar() },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                MapViewWithMarker(36.105994, 128.425637)

                ViewPager(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }

    )
}

@Composable
fun MapViewWithMarker(latitude: Double, longitude: Double) {
    val mapViewState = ComposeMapView(latitude, longitude)
    val mapView = mapViewState.value

    mapView?.let { map ->
        LaunchedEffect(key1 = map) {
            val marker = MapPOIItem().apply {
                itemName = "MARKER_NAME"
                tag = 0
                markerType = MapPOIItem.MarkerType.CustomImage
                customImageResourceId = R.drawable.icon_marker
                isCustomImageAutoscale = false
                mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            }

            map.addPOIItem(marker)
            // 필요한 이벤트를 여기에 추가하세요.
            map.setPOIItemEventListener(object : MapView.POIItemEventListener {
                override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
// 여기에서 마커 클릭 이벤트를 처리합니다.
                }

                override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
                    // 마커의 말풍선 클릭 이벤트를 처리합니다.

                }

                override fun onCalloutBalloonOfPOIItemTouched(
                    p0: MapView?,
                    p1: MapPOIItem?,
                    p2: MapPOIItem.CalloutBalloonButtonType?
                ) {
                    // 마커의 말풍선 클릭 이벤트를 처리합니다.
                }

                override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
                    // 마커의 드래그 이벤트를 처리합니다
                }


            })
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