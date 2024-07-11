package com.example.compasschallenge.flow

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import setup.Feature

@Composable
fun rememberCompassAppState(
    navController: NavHostController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    systemUiController: SystemUiController = rememberSystemUiController().apply {
}

) = remember(navController) {
    CompassAppState(
        navController = navController,
        coroutineScope = coroutineScope,
        systemUiController = systemUiController,
        scaffoldState = scaffoldState
    )
}

class CompassAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val systemUiController: SystemUiController,
    val scaffoldState: ScaffoldState
){
    /*private val bottomBarRoutes =
        mutableListOf<String>(BottomNavItem.Account.screen_route, BottomNavItem.Buy.screen_route, BottomNavItem.Bookings.screen_route, BottomNavItem.Schedule.screen_route, BottomNavItem.Home.screen_route)*/

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route == Feature.MAIN.route
}
