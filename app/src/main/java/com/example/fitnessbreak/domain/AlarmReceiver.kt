package com.example.fitnessbreak.domain

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission

//class AlarmReceiver : BroadcastReceiver(){
//    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
//    override fun onReceive(context: Context, intent: Intent) {
//        // Создаём канал при первом вызове
//        NotificationHelper.createNotificationChannel(context)
//        // Показываем полноэкранное уведомление
//        NotificationHelper.showAlarmNotification(context)
//    }
//
//}