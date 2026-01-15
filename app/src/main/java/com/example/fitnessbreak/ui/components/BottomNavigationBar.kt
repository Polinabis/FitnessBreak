package com.example.fitnessbreak.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitnessbreak.R
import com.example.fitnessbreak.navigation.NavRoutes
import com.example.fitnessbreak.ui.theme.Background

@Composable
fun BottomNavigationBar(navController: NavController) {

    // Подписываемся на изменения маршрута
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(bottom = 5.dp)
            .systemBarsPadding(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        BottomNavigationItem(
            title = "Напоминания",
            isSelected = currentRoute == NavRoutes.REMINDER,
            selectedIcon = R.drawable.alarm_tangerine,
            unselectedIcon = R.drawable.alarm_dark_green
        ) {
            navController.navigate(NavRoutes.REMINDER) {
                popUpTo(navController.graph.id) // очищаем стек
                launchSingleTop = true // избегаем дубликатов
            }
        }

        Spacer(Modifier.width(20.dp))

        BottomNavigationItem(
            title = "Упражнения",
            isSelected = currentRoute == NavRoutes.EXERCISE_SETTINGS,
            selectedIcon = R.drawable.excersize_tangerine,
            unselectedIcon = R.drawable.excersize_dark_green
        ) {
            navController.navigate(NavRoutes.EXERCISE_SETTINGS) {
                popUpTo(navController.graph.id)
                launchSingleTop = true
            }
        }
    }
}
