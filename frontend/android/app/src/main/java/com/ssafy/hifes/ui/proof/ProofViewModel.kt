package com.ssafy.hifes.ui.proof

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.hifes.data.model.ProofResponseType
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.math.log

private const val TAG = "ProofViewModel"
class ProofViewModel: ViewModel() {
    private var _stampProofResponse : MutableLiveData<ProofResponseType> = MutableLiveData()
    val stampProofResponse : LiveData<ProofResponseType> = _stampProofResponse

    private var _festivalProofResponse : MutableLiveData<ProofResponseType> = MutableLiveData()
    val festivalProofResponse : LiveData<ProofResponseType> = _festivalProofResponse

    init {
        _stampProofResponse.postValue(ProofResponseType.LOADING)
        _festivalProofResponse.postValue(ProofResponseType.LOADING)
    }

    fun requestStampProof(id : String){
        Log.d(TAG, "requestStampProof: ${id}로 전송")
        Timer().schedule(3000){
            _stampProofResponse.postValue(ProofResponseType.SUCESS)
        }
    }

    fun requestFestivalProof(id : String){
        Log.d(TAG, "requestStampProof: ${id}로 전송")
        Timer().schedule(3000){
            _festivalProofResponse.postValue(ProofResponseType.SUCESS)
        }
    }
}