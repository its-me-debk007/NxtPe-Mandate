package com.example.nxtpetask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nxtpetask.model.MandateDTO
import com.example.nxtpetask.repository.Repository
import com.example.nxtpetask.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getMandateDetails(): StateFlow<ApiState<MandateDTO>> =
        repository.getMandateDetails().stateIn(
            scope = viewModelScope,
            initialValue = ApiState.Loading(),
            started = SharingStarted.WhileSubscribed(5000)
        )
}