package com.example.compasschallenge.flow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = wordCounterList,
                    itemContent = {
                        TenCharactersItem(character = it)
                    }
                )
            }
    }

}

@Composable
fun TenCharactersItem(character: String) {
    Row {
        Column {
            Text(text = character)
        }
    }
}
