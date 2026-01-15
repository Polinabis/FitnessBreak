package com.example.fitnessbreak.ui.screens.reminder

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessbreak.ui.components.ScreenTemplate

@Composable
fun ReminderScreen(
    viewModel: ReminderViewModel = viewModel()
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