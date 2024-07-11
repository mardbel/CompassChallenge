package com.example.compasschallenge.models

import androidx.compose.runtime.Immutable
import com.example.compasschallenge.utils.UiEvent
import com.example.compasschallenge.utils.UiState

@Immutable
sealed class MainScreenUiEvent : UiEvent {
    data class ShowLoading(val isLoading :Boolean) : MainScreenUiEvent()

    object HideAlertMessage : MainScreenUiEvent()
}

@Immutable
data class MainScreenState(
    val showLoading: Boolean = false,
    //val loggedIn: Boolean? = null,

    ) : UiState {
    companion object {
        fun initial() = MainScreenState()
    }
}