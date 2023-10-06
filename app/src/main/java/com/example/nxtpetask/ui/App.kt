package com.example.nxtpetask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nxtpetask.ui.screen.NxtDukaHome
import com.example.nxtpetask.ui.screen.SetLoginPin

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            NxtDukaHome(navController)
        }
        composable("pin") {
            SetLoginPin()
        }
    }
}