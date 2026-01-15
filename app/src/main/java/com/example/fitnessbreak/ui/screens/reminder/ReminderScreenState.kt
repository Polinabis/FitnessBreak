package com.example.fitnessbreak.ui.screens.reminder

sealed interface ReminderScreenState {
    data object Start : ReminderScreenState
    data object Continue : ReminderScreenState
    data object Pause : ReminderScreenState
}