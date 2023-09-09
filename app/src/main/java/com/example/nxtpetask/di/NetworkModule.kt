package com.example.nxtpetask.di

import com.example.nxtpetask.BuildConfig
import com.example.nxtpetask.network.ApiService
import com.example.nxtpetask.repository.Repository
import com.example.nxtpetask.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)
}