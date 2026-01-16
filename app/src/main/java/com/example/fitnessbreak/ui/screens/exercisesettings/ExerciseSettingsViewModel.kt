package com.example.fitnessbreak.ui.screens.exercisesettings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.viewModelScope
import com.example.fitnessbreak.data.local.model.ExerciseCard
import com.example.fitnessbreak.data.local.model.ExerciseSection
import com.example.fitnessbreak.data.repository.ExerciseCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.example.fitnessbreak.data.repository.SectionRepository
import kotlinx.coroutines.launch


@HiltViewModel
class ExerciseSettingsViewModel @Inject constructor(
    private val cardsRepository: ExerciseCardRepository,
    sectionsRepository: SectionRepository
) : ViewModel() {


    // Загружаем все карточки (если нужно отдельно)
    private val _cards = cardsRepository.getAllCards().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val cards: StateFlow<List<ExerciseCard>> = _cards

    // Загружаем все секции с полными карточками
    private val _sections = sectionsRepository.getAllSections().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val sections: StateFlow<List<ExerciseSection>> = _sections



    private val _originalSelection = mutableStateOf<Set<Int>>(emptySet())
    val originalSelection: State<Set<Int>> = _originalSelection

    private val _currentSelection = mutableStateOf<Set<Int>>(emptySet())
    val currentSelection: State<Set<Int>> = _currentSelection

    // Исходные значения (из БД)
    private val _originalReminderHours = mutableStateOf(0)
    private val _originalReminderMinutes = mutableStateOf(0)

    // Текущие значения (локальные)
    private val _currentReminderHours = mutableStateOf(1) // значение по умолчанию
    private val _currentReminderMinutes = mutableStateOf(0)

    val reminderHours: State<Int> = _currentReminderHours
    val reminderMinutes: State<Int> = _currentReminderMinutes

    val hasUnsavedChanges: State<Boolean> = derivedStateOf {
        // Изменения в карточках
        _originalSelection.value != _currentSelection.value ||
                // Или изменения во времени
                _originalReminderHours.value != _currentReminderHours.value ||
                _originalReminderMinutes.value != _currentReminderMinutes.value
    }

    // Загрузка данных (вызывается один раз при старте)
    fun loadInitialData(
        initialSelectedIds: Set<Int>,
        initialHours: Int = 1,
        initialMinutes: Int = 0
    ) {
        if (_originalSelection.value.isEmpty()) {
            _originalSelection.value = initialSelectedIds
            _currentSelection.value = initialSelectedIds

            _originalReminderHours.value = initialHours
            _originalReminderMinutes.value = initialMinutes
            _currentReminderHours.value = initialHours
            _currentReminderMinutes.value = initialMinutes
        }
    }

    // Переключение карточки (локально)
    fun toggleCardLocally(cardId: Int) {
        val current = _currentSelection.value
        _currentSelection.value = if (cardId in current) {
            current - cardId
        } else {
            current + cardId
        }
    }

    // Обновление времени (локально)
    fun updateReminderTime(hours: Int, minutes: Int) {
        // Валидация
        val validHours = hours.coerceIn(0, 23)
        val validMinutes = minutes.coerceIn(0, 55)
        _currentReminderHours.value = validHours
        _currentReminderMinutes.value = validMinutes
    }

    // Сохранение в БД
    fun saveSelection() {

        viewModelScope.launch {
            try {
                cardsRepository.updateCardsSelection(_currentSelection.value)

                // TODO: Сохранение времени напоминания
                // saveReminderTime(_currentReminderHours.value, _currentReminderMinutes.value)

                _originalSelection.value = _currentSelection.value
                _originalReminderHours.value = _currentReminderHours.value
                _originalReminderMinutes.value = _currentReminderMinutes.value

            } catch (e: Exception) {
                println("Ошибка сохранения: ${e.message}")
            }
        }


    }
}