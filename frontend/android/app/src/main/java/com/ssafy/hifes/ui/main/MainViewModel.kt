package com.ssafy.hifes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.data.model.PostDto
import java.sql.Date
import java.text.SimpleDateFormat

class MainViewModel : ViewModel() {
    private var _festivalList: MutableLiveData<MutableList<OrganizedFestivalDto>> =
        MutableLiveData()
    val festivalList: LiveData<MutableList<OrganizedFestivalDto>> = _festivalList

    private var _selectedFestival : MutableLiveData<OrganizedFestivalDto> = MutableLiveData()
    val selectedFestival : LiveData<OrganizedFestivalDto> = _selectedFestival

    var testDate: Date

    init {
        val formatter = SimpleDateFormat("yyyy.MM.dd")
        testDate = Date(formatter.parse("2023.04.25").time)
        getOrganizedFestivalList()
    }


    fun getOrganizedFestivalList() {//추후 서버 통신 코드가 생기면 이 부분을 서버에게서 공지 게시글 리스트를 받아오는것으로 변경한다
        var festivalListDummyData = mutableListOf<OrganizedFestivalDto>()
        festivalListDummyData.apply {
            add(
                OrganizedFestivalDto(
                    1,
                    1,
                    "대구치맥페스티벌",
                    "https://picsum.photos/600",
                    testDate,
                    testDate,
                    "festival Outline",
                    "festival Address",
                    36.105995,
                    128.42564
                )
            )

            add(
                OrganizedFestivalDto(
                    1,
                    1,
                    "대구치맥페스티벌",
                    "https://picsum.photos/600",
                    testDate,
                    testDate,
                    "festival Outline",
                    "festival Address",
                    36.105894,
                    128.42573
                )
            )
            add(
                OrganizedFestivalDto(
                    1,
                    1,
                    "대구치맥페스티벌",
                    "https://picsum.photos/600",
                    testDate,
                    testDate,
                    "festival Outline",
                    "festival Address",
                    36.105993,
                    128.4263
                )
            )
        }
        _festivalList.postValue(festivalListDummyData)
    }

    fun getFestivalDetail(festival : OrganizedFestivalDto) {
        _selectedFestival.postValue(festival)
    }
}