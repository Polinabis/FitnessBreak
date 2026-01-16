package com.example.fitnessbreak.ui.screens.exercisesettings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessbreak.ui.components.ScreenTemplate
import com.example.fitnessbreak.ui.theme.Background
import com.example.fitnessbreak.ui.theme.Peachy
import com.example.fitnessbreak.ui.theme.SaveButtonStyle
import com.example.fitnessbreak.ui.theme.ScreenTitleStyle
import com.example.fitnessbreak.ui.theme.Tangerine

@Composable
fun ExerciseSettingsScreen(
    viewModel: ExerciseSettingsViewModel = hiltViewModel(),
    onNavigateToForm: () -> Unit
) {
    val cards by viewModel.cards.collectAsState()
    val sections by viewModel.sections.collectAsState()

    // Текущее состояние (локальное, до сохранения)
    val currentSelection by viewModel.currentSelection
    val hasUnsavedChanges by viewModel.hasUnsavedChanges
    val reminderHours by viewModel.reminderHours
    val reminderMinutes by viewModel.reminderMinutes

    ScreenTemplate(title = "Упражнения") {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Секции
            items(sections) { section ->
                ExerciseSectionItem(
                    section = section,
                    selectedCardIds = currentSelection,
                    onSwitchButtonClick = { cardId ->
                        viewModel.toggleCardLocally(cardId)
                    },
                    onNavigateToForm = onNavigateToForm
                )
            }

            // Подзаголовок
            item {
                Text(
                    text = "Периодичность напоминаний",
                    style = ScreenTitleStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Выбор времени
            item {
                ReminderTimePicker(
                    hours = reminderHours,
                    minutes = reminderMinutes,
                    onTimeChange = { hours, minutes ->
                        viewModel.updateReminderTime(hours, minutes)
                    }
                )
            }

            // Кнопка "Сохранить" — последний элемент списка
            item {
                Button(
                    onClick = { viewModel.saveSelection() },
                    enabled = hasUnsavedChanges,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Tangerine,
                        disabledContainerColor = Peachy,
                        contentColor = Background
                    )
                ) {
                    Text(
                        text = "Сохранить",
                        style = SaveButtonStyle
                    )
                }
            }
        }
    }
}

