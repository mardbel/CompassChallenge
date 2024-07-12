package com.example.compasschallenge.flow

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    goToResultsScreen: () -> Unit,

) {

    Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LaunchButton{ goToResultsScreen.invoke() }
            }
        }
}
@Composable
fun LaunchButton(goToResultsScreen: () -> Unit) {
    Button(
        onClick = {
            goToResultsScreen.invoke()
        },
        modifier = Modifier
            .background(Color.Black)
    ){
        Text(
            text = "Retrieve Data",
            color = Color.White,
            maxLines = 2
        )
    }
}

@Preview
@Composable
fun mainPreview() {
    LaunchButton {

    }
}


