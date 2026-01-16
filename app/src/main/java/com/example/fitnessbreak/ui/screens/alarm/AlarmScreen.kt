package com.example.fitnessbreak.ui.screens.alarm

import android.app.Activity
import android.view.WindowManager
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay


@Suppress("DEPRECATION")
@Composable
fun AlarmScreen(
    onNavigateToReminder: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: AlarmViewModel = viewModel()
) {
    val cards by viewModel.cards.collectAsState()

    // Полноэкранный режим (поверх всего)
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val window = (context as Activity).window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)

        onDispose {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    when (val state = viewModel.uiState.value) {
        AlarmScreenState.Initial -> {
            AlarmInitialContent(
                onStartClick = { viewModel.onStartClicked() },
                onPostponeClick = {
                    viewModel.onPostponeClicked()
                    onNavigateToReminder()
                }
            )
        }
        is AlarmScreenState.InProgress -> {
            val card = viewModel.getCardAt(state.currentCardIndex)
            if (card != null) {
                AlarmInProgressContent(
                    progress = viewModel.getProgress(),
                    card = card,
                    onDoneClick = { viewModel.onDoneClicked() }
                )
            } else {
                CircularProgressIndicator()
            }
        }

        // TODO вместо перехода какое-нибудь закрытие экрана по нажатию
        AlarmScreenState.Completed -> {
            AlarmCompletedContent()
            // Автоматический переход через 3 секунды
            LaunchedEffect(Unit) {
                delay(3000)
                onNavigateToHome()
            }
        }
    }
}