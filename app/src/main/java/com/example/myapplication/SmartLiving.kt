package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat

class SmartLiving : Application() {

    private var eventServiceRunning = false
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        if(!eventServiceRunning) {
            val intent = Intent(this, EventService::class.java)
            startService(intent)
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            with(NotificationManagerCompat.from(this)) {
                createNotificationChannel(channel)
            }
        }
    }

    companion object {
        const val CHANNEL_ID = "device"
    }
}