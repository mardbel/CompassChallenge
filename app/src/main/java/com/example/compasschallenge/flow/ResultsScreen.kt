package com.example.compasschallenge.flow

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compasschallenge.viewModels.ResultsViewModel
import org.koin.androidx.compose.get

@Composable
fun ResultsScreen(onBackPressed: () -> Unit) {
    val viewModel: ResultsViewModel = get()
    val state by viewModel.state.collectAsState()

    val everyTenCharacterList = state.everyTenCharacterRequest
    val wordCounterList = state.wordCounterRequest

    BackHandler {
        onBackPressed.invoke()
    }
    Row {
        Column(
            modifier = Modifier
                .background(Color.Cyan)
                .padding(16.dp)
                .weight(30F)
        ) {
            Text(
                text = "Every Ten Character",
                modifier = Modifier.padding(bottom = 8.dp),
            )
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = everyTenCharacterList,
                    itemContent = {
                        TenCharactersItem(character = it)
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .background(Color.Green)
                .padding(16.dp)
                .weight(70F)
        ) {
            Text(
                text = "Word frequency",
                modifier = Modifier.padding(bottom = 8.dp),
            )
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = wordCounterList.toList(),
                    itemContent = {
                        wordFrequencyItem(it)
                    }
                )
            }
        }
    }
}

@Composable
fun TenCharactersItem(character: Char) {
    Row {
        Column {
            Text(text = character.toString())
        }
    }
}
@Composable
fun wordFrequencyItem(pair: Pair<String, Int>) {
    Row {
        Column(modifier = Modifier
            .background(Color.Green)
            .padding(16.dp)
            .weight(80F)
        ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = pair.first)
                    Spacer(modifier = Modifier
                        .width(60.dp))
                    Text(
                        modifier = Modifier,
                        text = pair.second.toString())
                }
        }
    }
}
