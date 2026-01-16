package com.example.fitnessbreak.ui.screens.reminder

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnessbreak.domain.ReminderScheduler
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private val _uiState = mutableStateOf<ReminderScreenState>(ReminderScreenState.Start)
    val uiState: State<ReminderScreenState> = _uiState
    private val reminderScheduler = ReminderScheduler(application)

    fun onStartButtonClicked() {
        reminderScheduler.scheduleReminder(1)
        _uiState.value = ReminderScreenState.Continue
    }

    fun onStopButtonClicked() {
        reminderScheduler.cancelReminder()
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