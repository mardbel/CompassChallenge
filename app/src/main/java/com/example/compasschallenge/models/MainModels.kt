package com.example.compasschallenge.models

import androidx.compose.runtime.Immutable
import com.example.compasschallenge.utils.UiEvent
import com.example.compasschallenge.utils.UiState

@Immutable
sealed class ResultsScreenUiEvent : UiEvent {
    data class ShowLoading(val isLoading :Boolean) : ResultsScreenUiEvent()
    data class SomeRequestHasFinished(
        val everyTenCharacterRequest : List<String>,
        val wordCounterRequest : List<String>) : ResultsScreenUiEvent()

    object HideAlertMessage : ResultsScreenUiEvent()
}

@Immutable
data class ResultsScreenState(
    val showLoading: Boolean = false,
    val everyTenCharacterRequest: List<String> = listOf("everynull"),
    val wordCounterRequest: List<String> = listOf("counternull")

    ) : UiState {
    companion object {
        fun initial() = ResultsScreenState()
    }
}