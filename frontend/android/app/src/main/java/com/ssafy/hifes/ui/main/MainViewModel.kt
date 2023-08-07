package com.ssafy.hifes.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.ui.map.MapType
import java.sql.Date
import java.text.SimpleDateFormat

private const val TAG = "MainViewModel_하이페스"

class MainViewModel : ViewModel() {
    private var _festivalList: MutableLiveData<MutableList<OrganizedFestivalDto>> =
        MutableLiveData()
    val festivalList: LiveData<MutableList<OrganizedFestivalDto>> = _festivalList

    private var _selectedFestival: MutableLiveData<OrganizedFestivalDto> = MutableLiveData()
    val selectedFestival: LiveData<OrganizedFestivalDto> = _selectedFestival

    private var _mapType: MutableLiveData<MapType> = MutableLiveData()
    val mapType: LiveData<MapType> = _mapType

    var testDate: Date
    val festOutline =
        "하늘이 내린 최고의 조합, 치킨과 맥주! 매년 여름, 대구에서 치킨과 맥주의 기막힌 조합을 테마로 한 대구치맥페스티벌이 열린다. 치맥페스티벌이라는 말 그대로 축제 기간 동안 맛있는 치킨과 시원한 맥주를 마음껏 즐기며 가수들의 공연을 관람할 수 있다. ..."

    init {
        val formatter = SimpleDateFormat("yyyy.MM.dd")
        testDate = Date(formatter.parse("2023.04.25").time)
        getOrganizedFestivalList()
    }


    // 홈 화면, 일반 맵 화면에서 사용하는 축제 리스트
    fun getOrganizedFestivalList() { //추후 서버 통신 코드가 생기면 이 부분을 서버에게서 축제 리스트를 받아오는것으로 변경한다
        var festivalListDummyData = mutableListOf<OrganizedFestivalDto>()
        festivalListDummyData.apply {
            add(
                OrganizedFestivalDto(
                    1,
                    1,
                    "2019 대구치맥페스티벌",
                    "https://picsum.photos/600",
                    testDate,
                    testDate,
                    festOutline,
                    "festival Address",
                    36.105995,
                    128.42564
                )
            )

            add(
                OrganizedFestivalDto(
                    1,
                    1,
                    "2020 대구치맥페스티벌",
                    "https://picsum.photos/600",
                    testDate,
                    testDate,
                    festOutline,
                    "festival Address",
                    36.107894,
                    128.42573
                )
            )
            add(
                OrganizedFestivalDto(
                    1,
                    1,
                    "2021 대구치맥페스티벌",
                    "https://picsum.photos/600",
                    testDate,
                    testDate,
                    festOutline,
                    "festival Address",
                    36.105993,
                    128.4263
                )
            )
        }
        _festivalList.postValue(festivalListDummyData)
    }

    fun getFestivalDetail(festival: OrganizedFestivalDto) {
        _selectedFestival.postValue(festival)
    }

    fun updateMapTypeFestival() {
        _mapType.postValue(MapType.FESTIVAL)
    }

    fun updateMapTypeGeneral() {
        _mapType.postValue(MapType.GENERAL)
        Log.d(TAG, "updateMapTypeGeneral: ")
    }
}