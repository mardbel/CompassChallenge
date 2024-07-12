package com.example.compasschallenge.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compasschallenge.flow.EveryTenCharactersUseCase
import com.example.compasschallenge.flow.WordCounterUseCase
import com.example.compasschallenge.models.ResultsScreenState
import com.example.compasschallenge.models.ResultsScreenUiEvent
import com.example.compasschallenge.utils.Reducer
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResultsViewModel(
    val everyTenCharactersUseCase: EveryTenCharactersUseCase,
    val wordCounterUseCase: WordCounterUseCase
): ViewModel() {
    private val reducer = ResultsReducer(ResultsScreenState.initial())
    val state: StateFlow<ResultsScreenState>
        get() = reducer.state

    init {
        extractTextsFromWeb()
    }

    private fun extractTextsFromWeb() {
        reducer.sendEvent(ResultsScreenUiEvent.ShowLoading(true))

        viewModelScope.launch {
                val filtered = everyTenCharactersUseCase()
                reducer.sendEvent(ResultsScreenUiEvent.EveryTenRequestHasFinished(filtered))
                val map = wordCounterUseCase()
                reducer.sendEvent(ResultsScreenUiEvent.WordFrequencyRequestHasFinished(map))
        }
        reducer.sendEvent(ResultsScreenUiEvent.ShowLoading(false))
    }

    inner class ResultsReducer(initial: ResultsScreenState) :
        Reducer<ResultsScreenState, ResultsScreenUiEvent>(initial) {

        override fun reduce(oldState: ResultsScreenState, event: ResultsScreenUiEvent) {
            when (event) {
                is ResultsScreenUiEvent.ShowLoading -> {
                    setState(oldState.copy(showLoading = event.isLoading))
                }
                is ResultsScreenUiEvent.EveryTenRequestHasFinished -> {
                    setState(oldState.copy(everyTenCharacterRequest = event.everyTenCharacterRequest))
                }
                is ResultsScreenUiEvent.WordFrequencyRequestHasFinished -> {
                    setState(oldState.copy(wordCounterRequest = event.wordCounterRequest))
                }
                else -> { //DO NOTHING}
                }
            }
        }

    }
}
