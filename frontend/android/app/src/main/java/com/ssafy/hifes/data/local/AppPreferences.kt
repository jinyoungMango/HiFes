package com.ssafy.hifes.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.ssafy.hifes.data.model.LoginResponse

object AppPreferences {
    private lateinit var preferences: SharedPreferences
    private val gson = GsonBuilder().create()

    private const val LOGIN_SESSION = "login.session"

    private const val ACCESS_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"
    private const val USER_ID = "user_id"
    private const val USER_NICKNAME = "user_nickname"
    private const val FCM_TOKEN = "fcm_token"

    private const val CALL_LATITUDE = "call_latitude"
    private const val CALL_LONGITUDE = "call_longitude"
    private const val CALL_FESTIVALID = "call_festival_id"


    fun openSharedPreferences(context: Context) {
        preferences = context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
    }

    fun initLoginInfo(loginResponse: LoginResponse) {
        preferences.edit().putString(ACCESS_TOKEN, loginResponse.accessToken)
            .putString(REFRESH_TOKEN, loginResponse.refreshToken)
            .putString(USER_ID, loginResponse.id)
            .putString(USER_NICKNAME, loginResponse.nickname)
            .commit()
    }

    // sharedPreferences에 저장된 정보 반환
    fun getAccessToken(): String? {
        return preferences.getString(ACCESS_TOKEN, "")
    }

    fun getRefreshToken(): String? {
        return preferences.getString(REFRESH_TOKEN, "")
    }

    fun getUserId(): String? {
        return preferences.getString(USER_ID, "")
    }

    fun getUserNickname(): String? {
        return preferences.getString(USER_NICKNAME, "")
    }

    fun removeAccessToken() {
        preferences.edit().remove(ACCESS_TOKEN).apply()
    }

    fun initFcmToken(fcmToken: String) {
        preferences.edit().putString(FCM_TOKEN, fcmToken).commit()
    }

    fun getFcmToken(): String {
        return preferences.getString(FCM_TOKEN, "") ?: ""
    }

    fun saveCallLocation(lat: String, lng: String, festivalId: String) {
        preferences.edit().putString(CALL_LATITUDE, lat)
            .putString(CALL_LONGITUDE, lng)
            .putString(CALL_FESTIVALID, festivalId)
            .commit()
    }

    fun getCallLocation(): Pair<String, List<String>> {
        val lat = preferences.getString(CALL_LATITUDE, "") ?: ""
        val lng = preferences.getString(CALL_LONGITUDE, "") ?: ""
        val festivalId = preferences.getString(CALL_FESTIVALID, "") ?: ""
        var latLngList = listOf<String>(lat, lng)

        return Pair(festivalId, latLngList)
    }

    fun removeCallLocation(){
        preferences.edit().putString(CALL_LATITUDE, "")
            .putString(CALL_LONGITUDE, "")
            .putString(CALL_FESTIVALID, "")
            .commit()
    }

}