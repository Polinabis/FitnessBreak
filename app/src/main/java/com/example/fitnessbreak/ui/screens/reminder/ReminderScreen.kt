package com.example.fitnessbreak.ui.screens.reminder

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessbreak.ui.components.ScreenTemplate

@Composable
fun ReminderScreen(
    viewModel: ReminderViewModel = hiltViewModel()
) {
    ScreenTemplate(title = "Напоминания") {

        when (val state = viewModel.uiState.value) {
            ReminderScreenState.Start -> {
                StartContent(
                    onStartButtonClick = { viewModel.onStartButtonClicked() }
                )
            }
            ReminderScreenState.Continue -> {
                ContinueContent(
                    onStopButtonClick = { viewModel.onStopButtonClicked() },
                    onPauseButtonClick = { viewModel.onPauseButtonClicked() }
                )
            }
            ReminderScreenState.Pause -> {
                PauseContent(
                    onStopButtonClick = { viewModel.onStopButtonClicked() },
                    onContinueButtonClick = { viewModel.onContinueButtonClicked() }
                )
            }
        }
    }
}