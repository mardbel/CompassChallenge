package com.example.compasschallenge.viewModels

import androidx.lifecycle.ViewModel
import com.example.compasschallenge.models.MainScreenState
import com.example.compasschallenge.models.MainScreenUiEvent
import com.example.compasschallenge.utils.Reducer
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    private val reducer = MainReducer(MainScreenState.initial())

    val state: StateFlow<MainScreenState>
        get() = reducer.state

    fun extractTextsFromWeb() {

    }

    inner class MainReducer(initial: MainScreenState) :
        Reducer<MainScreenState, MainScreenUiEvent>(initial) {

        override fun reduce(oldState: MainScreenState, event: MainScreenUiEvent) {
            when (event) {

                is MainScreenUiEvent.ShowLoading -> {
                    setState(oldState.copy(showLoading = event.isLoading))
                }

                else -> {}
            }
        }
    }


}