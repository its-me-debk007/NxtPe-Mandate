package com.example.nxtpetask.repository

import android.util.Log
import com.example.nxtpetask.model.MandateDTO
import com.example.nxtpetask.network.ApiService
import com.example.nxtpetask.util.ApiState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override suspend fun getMandateDetails(): ApiState<MandateDTO> = try {
        ApiState.Success(apiService.getMandateDetails())
    } catch (e: Exception) {
        Log.d("retro", e.message.toString())
        ApiState.Error(msg = e.message.toString())
    }
}