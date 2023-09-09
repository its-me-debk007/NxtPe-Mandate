package com.example.nxtpetask.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nxtpetask.ui.viewmodel.HomeViewModel
import com.example.nxtpetask.util.ApiState

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    when (viewModel.mandateState.value) {
        is ApiState.Loading -> {
            Log.d("retro", "loading")
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ApiState.Error -> {
            Log.d("retro", viewModel.mandateState.value.errorMsg!!)
        }

        is ApiState.Success -> {
            Log.d("retro", viewModel.mandateState.value.data!!.cancel_text)
        }
    }
}