package com.example.fitnessbreak.ui.navigation // ← должно совпадать с NavRoutes!

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnessbreak.navigation.NavRoutes
import com.example.fitnessbreak.ui.screens.alarm.AlarmScreen
import com.example.fitnessbreak.ui.screens.exercisesettings.ExerciseSettingsScreen
import com.example.fitnessbreak.ui.screens.exerciseform.ExerciseFormScreen
import com.example.fitnessbreak.ui.screens.reminder.ReminderScreen

@Composable
fun FitnessBreakNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NavRoutes.REMINDER
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavRoutes.REMINDER) {
            ReminderScreen()
        }

        composable(NavRoutes.EXERCISE_SETTINGS) {
            ExerciseSettingsScreen(
                onNavigateToForm = {
                    navController.navigate(NavRoutes.EXERCISE_FORM)
                }
            )
        }

        composable(NavRoutes.EXERCISE_FORM) {
            ExerciseFormScreen(
                onExerciseSaved = {
                    navController.popBackStack() // возврат к настройкам
                }
            )
        }
    }
}