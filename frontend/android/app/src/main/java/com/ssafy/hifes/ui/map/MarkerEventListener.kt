package com.ssafy.hifes.ui.map

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

private const val TAG = "MarkerEventListener_하이"
class MarkerEventListener() : MapView.POIItemEventListener {
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        Log.d(TAG, "onPOIItemSelected: 마커 클릭")
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        Log.d(TAG, "onPOIItemSelected: 마커 클릭")
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
        Log.d(TAG, "onPOIItemSelected: 마커 클릭")
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

    }
}