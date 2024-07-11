package com.example.compasschallenge.flow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun ResultsScreen() {
    val viewModel: ResultsViewModel = get()
    val state by viewModel.state.collectAsState()

    val everyTenCharacterList = state.everyTenCharacterRequest
    val wordCounterList = state.wordCounterRequest
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
                    items = wordCounterList.keys.toList(),
                    itemContent = {
                        wordFrequencyItem(wordCounterList)
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
fun wordFrequencyItem(map: Map<String, Int>) {
    Row {
        Column(modifier = Modifier
            .background(Color.Green)
            .padding(16.dp)
            .weight(80F)
        ) {

            map.forEach() {
                Text(text = it.key)// wordsFrequency.wordsFrequency.k ["hola"].k)
            }

        }
        Column(modifier = Modifier
            .background(Color.Yellow)
            .padding(16.dp)
            .weight(20F)
        ) {
            map.forEach() {
                Text(text = it.value.toString())
            }
        }
    }
}
