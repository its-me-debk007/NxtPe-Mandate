package com.example.nxtpetask.repository

import com.example.nxtpetask.model.MandateDTO
import com.example.nxtpetask.util.ApiState
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getMandateDetails(): Flow<ApiState<MandateDTO>>
}