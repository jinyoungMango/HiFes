package com.ssafy.hifes.ui.proof

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.hifes.data.model.ProofResponseType
import com.ssafy.hifes.data.repository.proof.ProofRepository
import com.ssafy.hifes.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ProofViewModel"

@HiltViewModel
class ProofViewModel @Inject constructor(
    private val repository: ProofRepository
) : ViewModel() {

    private var _stampProofResponse: MutableLiveData<Pair<ProofResponseType, Boolean>> =
        MutableLiveData()
    val stampProofResponse: LiveData<Pair<ProofResponseType, Boolean>> = _stampProofResponse

    private var _festivalProofResponse: MutableLiveData<Pair<ProofResponseType, Boolean>> =
        MutableLiveData()
    val festivalProofResponse: LiveData<Pair<ProofResponseType, Boolean>> = _festivalProofResponse

    fun requestFestivalProof(festivalId: Int, userId: String?) {

        viewModelScope.launch {
            if (userId != null) {
                val response = repository.participateFestival(userId, festivalId)

                when (response) {
                    is NetworkResponse.Success -> {
                        _festivalProofResponse.postValue(
                            Pair(
                                ProofResponseType.SUCESS,
                                response.body
                            )
                        )
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

    fun requestStampProof(stampId: Int, userId: String?) {
        viewModelScope.launch {
            if (userId != null) {
                val response = repository.completeMission(userId, stampId)

                when (response) {
                    is NetworkResponse.Success -> {
                        _stampProofResponse.postValue(Pair(ProofResponseType.SUCESS, response.body))
                    }

                    is NetworkResponse.ApiError -> {
                        Log.d(TAG, "requestFestivalProof: api errror")
                        _stampProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
                    }

                    is NetworkResponse.NetworkError -> {
                        Log.d(TAG, "requestFestivalProof: network err")
                        _stampProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
                    }

                    is NetworkResponse.UnknownError -> {
                        Log.d(TAG, "requestFestivalProof: unknown err")
                        _stampProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
                    }
                }
            } else {
                _stampProofResponse.postValue(Pair(ProofResponseType.FAIL, false))
            }

        }
    }

}