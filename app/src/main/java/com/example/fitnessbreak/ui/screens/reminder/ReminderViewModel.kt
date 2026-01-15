package com.example.fitnessbreak.ui.screens.reminder

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ReminderViewModel: ViewModel() {
    private val _uiState = mutableStateOf<ReminderScreenState>(ReminderScreenState.Start)
    val uiState: State<ReminderScreenState> = _uiState

    fun onStartButtonClicked() {
        doSomethingImportant()
        _uiState.value = ReminderScreenState.Continue
    }

    fun onStopButtonClicked() {
        // TODO: ф-ция, которая стопает таймер
        _uiState.value = ReminderScreenState.Start
    }

    fun onPauseButtonClicked() {
        // TODO: ф-ция, которая стопает таймер
        _uiState.value = ReminderScreenState.Pause
    }

    fun onContinueButtonClicked() {
        // TODO: ф-ция, которая запускает таймер
        _uiState.value = ReminderScreenState.Continue
    }

    private fun doSomethingImportant() { /* ... */ }
}