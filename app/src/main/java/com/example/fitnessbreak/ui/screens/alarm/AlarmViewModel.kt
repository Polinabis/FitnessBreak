package com.example.fitnessbreak.ui.screens.alarm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnessbreak.domain.model.ExerciseCard
import com.example.fitnessbreak.R
import androidx.compose.runtime.State
import com.example.fitnessbreak.domain.model.ImageSource

class AlarmViewModel : ViewModel() {
    private val _uiState = mutableStateOf<AlarmScreenState>(AlarmScreenState.Initial)
    val uiState: State<AlarmScreenState> = _uiState

    private val _cards = listOf(
        ExerciseCard(1, "Боковые наклоны головы", true, ImageSource.Resource(R.drawable.ic_launcher_background)),
        ExerciseCard(2, "Шея", true, ImageSource.Resource(R.drawable.ic_launcher_background)),
        ExerciseCard(3, "Спина", true, ImageSource.Resource(R.drawable.ic_launcher_background))
    )

    fun onStartClicked() {
        _uiState.value = AlarmScreenState.InProgress(
            currentCardIndex = 0,
            totalCards = _cards.size
        )
    }

    fun onPostponeClicked() {
        // Ничего не делаем — навигация обрабатывается во View
    }

    fun onDoneClicked() {
        val currentState = _uiState.value
        if (currentState is AlarmScreenState.InProgress) {
            val nextIndex = currentState.currentCardIndex + 1
            if (nextIndex >= _cards.size) {
                _uiState.value = AlarmScreenState.Completed
            } else {
                _uiState.value = AlarmScreenState.InProgress(
                    currentCardIndex = nextIndex,
                    totalCards = _cards.size
                )
            }
        }
    }

    fun getCardAt(index: Int): ExerciseCard? {
        return if (index in _cards.indices) _cards[index] else null
    }

    fun getProgress(): Float {
        val state = _uiState.value
        return if (state is AlarmScreenState.InProgress) {
            (state.currentCardIndex + 1).toFloat() / state.totalCards
        } else 0f
    }
}