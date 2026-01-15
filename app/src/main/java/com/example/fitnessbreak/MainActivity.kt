package com.example.fitnessbreak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnessbreak.ui.components.BottomNavigationBar
import com.example.fitnessbreak.ui.components.BottomNavigationItem
import com.example.fitnessbreak.ui.navigation.FitnessBreakNavGraph
import com.example.fitnessbreak.ui.screens.alarm.AlarmScreen
import com.example.fitnessbreak.ui.screens.exerciseform.ExerciseFormScreen
import com.example.fitnessbreak.ui.theme.FitnessBreakTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            FitnessBreakTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.systemBarsPadding(),
                    bottomBar = {
                        if (currentRoute == "reminder" || currentRoute == "exercise_settings") {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    FitnessBreakNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

        }
    }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )}

//                AlarmScreen(
//                    onNavigateToReminder = { println("Go to Reminder") },
//                    onNavigateToHome = { println("Go to Home") }
//                )
                }
            }
