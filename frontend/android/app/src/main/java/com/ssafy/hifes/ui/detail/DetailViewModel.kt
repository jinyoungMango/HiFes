package com.ssafy.hifes.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.hifes.data.model.MarkerDto

class DetailViewModel : ViewModel() {
    private var _markerList: MutableLiveData<MutableList<MarkerDto>> =
        MutableLiveData()
    val markerList: LiveData<MutableList<MarkerDto>> = _markerList

    private var _selectedBoothChip : MutableLiveData<Int> = MutableLiveData()
    val selectedBoothChip : LiveData<Int> = _selectedBoothChip

    init {
        getMarkerList()
    }

    // 축제 맵에서 사용하는 마커 리스트
    fun getMarkerList() { //추후 서버 통신 코드가 생기면 이 부분을 서버에게서 마커 리스트를 받아오는것으로 변경한다
        var markerListDummyData = mutableListOf<MarkerDto>()
        markerListDummyData.apply {
            add(
                MarkerDto(
                    1,
                    1,
                    1,
                    "판매 부스1",
                    36.106995,
                    128.420854,
                    "https://picsum.photos/600",
                    "판매 부스1 설명",
                    1,
                    "color"
                )
            )
            add(
                MarkerDto(
                    1,
                    1,
                    1,
                    "먹거리 부스1",
                    36.106217,
                    128.420875,
                    "https://picsum.photos/600",
                    "먹기리 부스1 설명",
                    2,
                    "color"
                )
            )
            add(
                MarkerDto(
                    1,
                    1,
                    1,
                    "식당 부스1",
                    36.107060,
                    128.423344,
                    "https://picsum.photos/600",
                    "식당 부스1 설명",
                    3,
                    "color"
                )
            )
            add(
                MarkerDto(
                    1,
                    1,
                    1,
                    "식당 부스2",
                    36.107360,
                    128.425344,
                    "https://picsum.photos/600",
                    "식당 부스2 설명 식당 부스2 설명 식당 부스2 설명 식당 부스2 설명",
                    3,
                    "color"
                )
            )
            add(
                MarkerDto(
                    1,
                    1,
                    1,
                    "식당 부스3",
                    36.107560,
                    128.428344,
                    "https://picsum.photos/600",
                    "식당 부스3 설명",
                    3,
                    "color"
                )
            )
        }
        _markerList.postValue(markerListDummyData)
    }

    fun updateSelectedBoothChip(index: Int) {
        _selectedBoothChip.postValue(index)
    }

}