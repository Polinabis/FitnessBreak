package com.example.fitnessbreak

import android.app.Application
import com.example.fitnessbreak.domain.NotificationHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitnessBreakApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }
}