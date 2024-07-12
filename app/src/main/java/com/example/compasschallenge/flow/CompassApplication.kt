package com.example.compasschallenge.flow

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CompassApp(
    navController: NavHostController
) {
    val appState: CompassAppState = rememberCompassAppState(
        navController
    )
    CompassMainScreen(appState = appState) {
        Scaffold() {
            Navigation(appState = appState)
        }
    }
}

    @Composable
    fun CompassMainScreen(appState: CompassAppState, content: @Composable () -> Unit) {
        CompassAppTheme() {
            Surface(color = MaterialTheme.colorScheme.primary) {
                content()
            }
        }

    }


