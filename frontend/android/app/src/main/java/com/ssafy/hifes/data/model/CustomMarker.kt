package com.ssafy.hifes.data.model

import com.naver.maps.map.overlay.Marker

data class CustomMarker(
    val marker: Marker,
    val boothName: String,
    val description: String
)