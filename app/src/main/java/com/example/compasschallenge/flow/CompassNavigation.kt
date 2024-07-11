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
                    goToWelcomeScreen = {
                        appState.navController.navigate(Feature.WELCOME.route) {
                            popUpTo(Feature.SPLASH.route)
                        }
                    }
                )
            }
        )
        composable(
            route = Feature.DOWN_SYSTEM.route,
            content = {
                appState.systemUiController.setSystemBarsColor(color = MaterialTheme.colorScheme.background)
                /*ResultScreen(
                    goToSplash = {
                        appState.navController.navigate(Feature.SPLASH.route) {
                            popUpTo(Feature.DOWN_SYSTEM.route){
                                inclusive = true
                            }
                        }
                    },
                    goToWebView = { url ->
                        val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                        appState.navController.navigate(Feature.WEB_VIEW.route + "/${encodedUrl}")
                    }
                )*/
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