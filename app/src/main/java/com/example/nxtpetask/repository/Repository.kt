package com.example.nxtpetask.repository

import com.example.nxtpetask.model.MandateDTO
import com.example.nxtpetask.util.ApiState

interface Repository {
    suspend fun getMandateDetails(): ApiState<MandateDTO>
}