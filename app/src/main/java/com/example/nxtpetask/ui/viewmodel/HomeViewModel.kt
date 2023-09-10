package com.example.nxtpetask.ui.viewmodel

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private val _timer: MutableState<Long> = mutableStateOf(0)
    val timer: State<Long> get() = _timer

    var showLoadingDialog by mutableStateOf(false)
    var showConfirmationDialog by mutableStateOf(false)

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

    val countDownTimer = object : CountDownTimer(20000, 1000L) {
        override fun onTick(millisUntilFinished: Long) {
            _timer.value = millisUntilFinished / 1000
            if (timer.value == 14L) {
                showLoadingDialog = false
                showConfirmationDialog = true
            }
            else if (timer.value == 8L) {
                showConfirmationDialog = false
                cancel()
            }
        }

        override fun onFinish() {
        }
    }
}