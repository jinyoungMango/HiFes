package com.ssafy.hifes.data.repository.group

import com.ssafy.hifes.data.model.ErrorResponse
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.util.network.NetworkResponse

interface GroupRepository {
    suspend fun getAllGroupList(): NetworkResponse<List<Group>, ErrorResponse>

}