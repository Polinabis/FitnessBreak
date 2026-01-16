package com.example.fitnessbreak

import android.os.Bundle
import android.view.WindowManager.LayoutParams
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.fitnessbreak.domain.ReminderScheduler
import com.example.fitnessbreak.ui.screens.alarm.AlarmScreen
import com.example.fitnessbreak.ui.theme.FitnessBreakTheme
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class   AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.addFlags(
            LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    LayoutParams.FLAG_TURN_SCREEN_ON or
                    LayoutParams.FLAG_KEEP_SCREEN_ON or
                    LayoutParams.FLAG_DISMISS_KEYGUARD
        )

        setContent {
            FitnessBreakTheme {
                AlarmScreen(
                    onNavigateToReminder = {
                        println("Go to Reminder")
                        finish()
                                           },
                    onNavigateToHome = {
                        println("Go to Home")
                        ReminderScheduler(this).scheduleReminder(15)
                        finish()
                    }
                )
            }
        }
    }
}