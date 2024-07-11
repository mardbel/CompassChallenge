package com.example.compasschallenge.flow

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compasschallenge.viewModels.MainViewModel
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    goToWelcomeScreen: () -> Unit,
) {
    val viewModel: MainViewModel = get()
    val state by viewModel.state.collectAsState()

    Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LaunchButton()
            }
        }

}
@Composable
fun LaunchButton() {
    Button(onClick = {  },
        modifier = Modifier
            .background(Color.Black)
            .height(40.dp)
            .width(200.dp),
    ){
        Text(
            text = "Do your think",
            modifier = Modifier
                .background(Color.Black),
            color = Color.White,
            maxLines = 2
        )
    }
}


