package com.example.nxtpetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.nxtpetask.ui.App
import com.example.nxtpetask.ui.screen.HomeScreen
import com.example.nxtpetask.ui.screen.NxtDukaHome
import com.example.nxtpetask.ui.screen.SetLoginPin
import com.example.nxtpetask.ui.screen.SetUpPinPage
import com.example.nxtpetask.ui.screen.readSimCardInfo
import com.example.nxtpetask.ui.theme.NxtPeTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NxtPeTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                   App()
                }
            }
        }
    }
}