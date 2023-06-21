package com.example.myapplication

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ShowNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val deviceId: String? = intent.getStringExtra(MyIntent.DEVICE_ID)
        Log.d(TAG, "Show notification intent received ${deviceId}")

        showNotification(context, deviceId!!)
    }


    private fun showNotification(context: Context, deviceId: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(MyIntent.DEVICE_ID, deviceId)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, SmartLiving.CHANNEL_ID)
            .setSmallIcon(R.drawable.aspiradora) //TODO CAMBIAR
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_text))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(context.getString(R.string.notification_text2))
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            val notificationManager = NotificationManagerCompat.from(context)
            if (notificationManager.areNotificationsEnabled())
                notificationManager.notify(deviceId.hashCode(), builder.build())
        } catch (e: SecurityException) {
            Log.d(TAG, "Notification permission not granted $e")
        }
        // Forma de identificar a la notificación, tiene que ir variando
        // Mapa (idDisp(Api) -> idNotifAsociadoAlDisp(notificationId))
    }

companion object {
        private const val TAG = "ShowNotificationReceiver"
    }
}