package com.example.nxtpetask.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nxtpetask.model.MandateDTO
import com.example.nxtpetask.repository.Repository
import com.example.nxtpetask.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getMandateDetails()
    }

    private val _mandateState: MutableState<ApiState<MandateDTO>> =
        mutableStateOf(ApiState.Loading())
    val mandateState: State<ApiState<MandateDTO>> get() = _mandateState

    private fun getMandateDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMandateDetails()
            _mandateState.value = when (response) {
                is ApiState.Loading -> ApiState.Loading()

                is ApiState.Error -> ApiState.Error(response.errorMsg.toString())

                is ApiState.Success -> ApiState.Success(response.data)
            }
        }
    }
}