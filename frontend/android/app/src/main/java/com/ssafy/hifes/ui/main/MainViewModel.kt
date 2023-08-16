package com.ssafy.hifes.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.hifes.data.model.Event
import com.ssafy.hifes.data.model.OrganizedFestivalDto
import com.ssafy.hifes.data.repository.main.MainRepository
import com.ssafy.hifes.ui.group.GroupScreenType
import com.ssafy.hifes.ui.map.MapType
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "MainViewModel_하이페스"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _msg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _msg

    private var _festivalList: MutableLiveData<List<OrganizedFestivalDto>> =
        MutableLiveData()
    val festivalList: LiveData<List<OrganizedFestivalDto>> = _festivalList

    private var _randomFestivalList: MutableLiveData<List<OrganizedFestivalDto>> =
        MutableLiveData()
    val randomFestivalList: LiveData<List<OrganizedFestivalDto>> = _randomFestivalList

    private var _festivalInfo: MutableLiveData<OrganizedFestivalDto> = MutableLiveData()
    val festivalInfo: LiveData<OrganizedFestivalDto> = _festivalInfo

    private var _mapType: MutableLiveData<MapType> = MutableLiveData()
    val mapType: LiveData<MapType> = _mapType

    private var _groupScreenType: MutableLiveData<GroupScreenType> = MutableLiveData()
    val groupScreenType: LiveData<GroupScreenType> = _groupScreenType

    private var _location: MutableLiveData<Location> = MutableLiveData()
    val location: LiveData<Location> = _location

    var selectedFestival: Int = -1



    // 홈 화면, 일반 맵 화면에서 사용하는 축제 리스트
    fun getNearFestivalList(userLatitude: Double, userLongitude: Double) {
        viewModelScope.launch {
            val response = repository.nearbyFestivals(userLatitude, userLongitude)
            val type = "token 정보 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "getNearFestivalList: $response")
                    _festivalList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    suspend fun fetchCurrentLocation(context: Context) {
        withContext(Dispatchers.IO) {
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
            }
            _location.postValue(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER))
//            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
    }

    fun getRandomFestivalList() {
        viewModelScope.launch {
            val response = repository.randomFestivals()
            val type = "token 정보 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "getRandomFestivalList: $response")
                    _randomFestivalList.postValue(response.body)
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun getFestivalInfo(festivalId: Int) {
        viewModelScope.launch {
            val response = repository.getFestivalInfo(festivalId)
            val type = "token 정보 조회에"
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d(TAG, "getNearFestivalList: $response")
                    _festivalInfo.postValue(response.body)
                    selectedFestival = response.body.festivalId
                }

                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }

                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }

                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun updateMapTypeFestival() {
        _mapType.postValue(MapType.FESTIVAL)
    }

    fun updateMapTypeGeneral() {
        _mapType.postValue(MapType.GENERAL)
        Log.d(TAG, "updateMapTypeGeneral: ")
    }

    fun updateGroupScreenTypeFestival() {
        _groupScreenType.postValue(GroupScreenType.FESTIVAL)
    }

    fun updateGroupScreenTypeAll() {
        _groupScreenType.postValue(GroupScreenType.All)
    }

    private fun postValueEvent(value: Int, type: String) {
        val msgArrayList = arrayOf(
            "Api 오류 : $type 실패했습니다.",
            "서버 오류 : $type 실패했습니다.",
            "알 수 없는 오류 : $type 실패했습니다."
        )

        when (value) {
            0 -> _msg.postValue(Event(msgArrayList[0]))
            1 -> _msg.postValue(Event(msgArrayList[1]))
            2 -> _msg.postValue(Event(msgArrayList[2]))
        }
    }
}