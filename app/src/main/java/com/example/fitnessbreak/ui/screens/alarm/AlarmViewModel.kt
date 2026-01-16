package com.example.fitnessbreak.ui.screens.alarm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnessbreak.data.local.model.ExerciseCard
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.fitnessbreak.data.repository.ExerciseCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    cardsRepository: ExerciseCardRepository
) : ViewModel() {
    private val _uiState = mutableStateOf<AlarmScreenState>(AlarmScreenState.Initial)
    val uiState: State<AlarmScreenState> = _uiState

    private val _cards = cardsRepository.getAllCards().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val cards: StateFlow<List<ExerciseCard>> = _cards

//    private val _cards = listOf(
//        ExerciseCard(1, "Боковые наклоны головы", true, ImageSource.Resource(R.drawable.ic_launcher_background)),
//        ExerciseCard(2, "Шея", true, ImageSource.Resource(R.drawable.ic_launcher_background)),
//        ExerciseCard(3, "Спина", true, ImageSource.Resource(R.drawable.ic_launcher_background))
//    )

    fun onStartClicked() {
        if (_cards.value.isEmpty()) {
            // Данные ещё не подгрузились из БД
            return
        }
        _uiState.value = AlarmScreenState.InProgress(
            currentCardIndex = 0,
            totalCards = _cards.value.size
        )
    }

    fun onPostponeClicked() {
        // Ничего не делаем — навигация обрабатывается во View
    }

    fun onDoneClicked() {
        val currentState = _uiState.value
        println("CARDS: $_cards")

        if (currentState is AlarmScreenState.InProgress) {
            val nextIndex = currentState.currentCardIndex + 1
            if (nextIndex >= _cards.value.size) {
                _uiState.value = AlarmScreenState.Completed
            } else {
                _uiState.value = AlarmScreenState.InProgress(
                    currentCardIndex = nextIndex,
                    totalCards = _cards.value.size
                )
            }
        }
    }

    fun getCardAt(index: Int): ExerciseCard? {
        return if (index in _cards.value.indices) _cards.value[index] else null
    }

    fun getProgress(): Float {
        val state = _uiState.value
        return if (state is AlarmScreenState.InProgress) {
            (state.currentCardIndex + 1).toFloat() / state.totalCards
        } else 0f
    }
}