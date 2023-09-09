package com.example.nxtpetask.repository

import android.util.Log
import com.example.nxtpetask.model.MandateDTO
import com.example.nxtpetask.network.ApiService
import com.example.nxtpetask.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override fun getMandateDetails(): Flow<ApiState<MandateDTO>> = flow {
        emit(ApiState.Loading())

        emit(ApiState.Success(apiService.getMandateDetails()))

    }.catch { e ->
        Log.d("retro", e.message.toString())
        emit(ApiState.Error(msg = e.message.toString()))
    }
}