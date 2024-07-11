package com.example.compasschallenge.models

import androidx.compose.runtime.Immutable
import com.example.compasschallenge.utils.UiEvent
import com.example.compasschallenge.utils.UiState

@Immutable
sealed class ResultsScreenUiEvent : UiEvent {
    data class ShowLoading(val isLoading :Boolean) : ResultsScreenUiEvent()
    data class SomeRequestHasFinished(
        val everyTenCharacterRequest : List<Char>,
        val wordCounterRequest : Map<String, Int>) : ResultsScreenUiEvent()

    object HideAlertMessage : ResultsScreenUiEvent()
}

@Immutable
data class ResultsScreenState(
    val showLoading: Boolean = false,
    val everyTenCharacterRequest: List<Char> = listOf('d'),
    val wordCounterRequest: Map<String, Int> = mapOf("counternull" to 1)

    ) : UiState {
    companion object {
        fun initial() = ResultsScreenState()
    }
}