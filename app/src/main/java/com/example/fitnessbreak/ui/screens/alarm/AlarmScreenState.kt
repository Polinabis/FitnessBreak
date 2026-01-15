package com.example.fitnessbreak.ui.screens.alarm

sealed interface AlarmScreenState {
    data object Initial : AlarmScreenState
    data class InProgress(
        val currentCardIndex: Int,
        val totalCards: Int
    ) : AlarmScreenState
    data object Completed : AlarmScreenState
}