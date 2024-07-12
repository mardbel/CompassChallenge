package com.example.compasschallenge.flow

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import setup.Feature
import setup.NavCommand
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun Navigation(appState: CompassAppState) {
    NavHost(
        navController = appState.navController,
        startDestination = Feature.MAIN.route
    ) {
        mainNav(appState = appState)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.mainNav(appState: CompassAppState) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.MAIN).route,
        route = Feature.MAIN.route
    ) {
        composable(
            navCommand = NavCommand.ContentType(Feature.MAIN),
            content = {
                MainScreen(
                    goToResultsScreen  = {
                        appState.navController.navigate(Feature.RESULTS_SCREEN.route) {
                        }
                    },
                )
            }
        )
        composable(
            route = Feature.RESULTS_SCREEN.route,
            content = {
                ResultsScreen(
                    onBackPressed = {
                        appState.navController.popBackStack()
                    }
                )
            }
        )
    }
}
private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}