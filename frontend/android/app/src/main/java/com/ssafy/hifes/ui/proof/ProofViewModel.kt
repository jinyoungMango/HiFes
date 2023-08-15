package com.ssafy.hifes.ui.proof

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.hifes.data.local.AppPreferences
import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.ProofResponseType
import com.ssafy.hifes.data.repository.proof.ProofRepository
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

private const val TAG = "ProofViewModel"

@HiltViewModel
class ProofViewModel @Inject constructor(
    private val repository: ProofRepository
) : ViewModel() {
    val userId = AppPreferences.getUserId()

    private var _stampProofResponse: MutableLiveData<ProofResponseType> = MutableLiveData()
    val stampProofResponse: LiveData<ProofResponseType> = _stampProofResponse

    private var _festivalProofResponse: MutableLiveData<Pair<ProofResponseType, Boolean>> = MutableLiveData()
    val festivalProofResponse: LiveData<Pair<ProofResponseType, Boolean>> = _festivalProofResponse

    fun requestFestivalProof(id: Int) {

        viewModelScope.launch {
            if (userId != null) {
                val response = repository.participateFestival(userId, id)
                _festivalProofResponse
                when (response) {
                    is NetworkResponse.Success -> {
                        _festivalProofResponse.postValue(Pair(ProofResponseType.SUCESS, response.body))
                    }

                    is NetworkResponse.ApiError -> {
                        Log.d(TAG, "requestFestivalProof: api errror")
                        _festivalProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
                    }

                    is NetworkResponse.NetworkError -> {
                        Log.d(TAG, "requestFestivalProof: network err")
                        _festivalProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
                    }

                    is NetworkResponse.UnknownError -> {
                        Log.d(TAG, "requestFestivalProof: unknown err")
                        _festivalProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
                    }
                }
            } else {
                _festivalProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
            }

        }
    }

    fun requestStampProof(id: Int) {
        Log.d(TAG, "requestStampProof: ${id}로 전송")
        Timer().schedule(3000) {
            _stampProofResponse.postValue(ProofResponseType.SUCESS)
        }
    }

}