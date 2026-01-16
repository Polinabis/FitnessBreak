package com.example.fitnessbreak

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnessbreak.ui.components.BottomNavigationBar
import com.example.fitnessbreak.ui.navigation.FitnessBreakNavGraph
import com.example.fitnessbreak.ui.theme.FitnessBreakTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }
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
