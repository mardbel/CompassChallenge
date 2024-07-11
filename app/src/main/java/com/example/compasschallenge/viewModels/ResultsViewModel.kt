package com.example.compasschallenge.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compasschallenge.models.ResultsScreenState
import com.example.compasschallenge.models.ResultsScreenUiEvent
import com.example.compasschallenge.utils.Reducer
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResultsViewModel(private val textRepository: TextRepository): ViewModel() {
    private val reducer = ResultsReducer(ResultsScreenState.initial())
    val state: StateFlow<ResultsScreenState>
        get() = reducer.state

    init {
        extractTextsFromWeb()
    }

    private fun extractTextsFromWeb() {
        reducer.sendEvent(ResultsScreenUiEvent.ShowLoading(true))

        val everyTenCharacterRequestDeferred = viewModelScope.async { textRepository.everyTenCharacterRequest() }
        val wordCounterRequestDeferred = viewModelScope.async {textRepository.wordCounterRequest() }

        viewModelScope.launch {
            val everyTenCharacterRequest = everyTenCharacterRequestDeferred.await()
            val wordCounterRequest = wordCounterRequestDeferred.await()
            reducer.sendEvent(ResultsScreenUiEvent.SomeRequestHasFinished(everyTenCharacterRequest, wordCounterRequest))
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
                is ResultsScreenUiEvent.SomeRequestHasFinished -> {
                    setState(oldState.copy(everyTenCharacterRequest = event.everyTenCharacterRequest, wordCounterRequest = event.wordCounterRequest))
                }
                else -> { //DO NOTHING}
                }
            }
        }

    }
}
