package com.example.nxtpetask.network

import com.example.nxtpetask.model.MandateDTO
import retrofit2.http.GET

interface ApiService {

    @GET("mandate/res.json")
    suspend fun getMandateDetails(): MandateDTO
}