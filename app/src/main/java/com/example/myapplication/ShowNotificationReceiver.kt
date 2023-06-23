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
        if(intent?.action == MyIntent.SHOW_NOTIFICATION) {
            val deviceId: String? = intent.getStringExtra(MyIntent.DEVICE_ID)
            val event: String? = intent.getStringExtra(MyIntent.EVENT)
            val args: String? = intent.getStringExtra(MyIntent.ARGS)
            Log.d(TAG, "Show notification intent received \"${event}->(args:${args}):${deviceId}\"")

            showNotification(context, deviceId!!, event!!, args!!)
        }
    }

    private fun showNotification(context: Context, deviceId: String, event: String, args: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(MyIntent.DEVICE_ID, deviceId)
            putExtra(MyIntent.EVENT, event)
            putExtra(MyIntent.ARGS, args)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        Log.d(TAG, " ARGS: $args")

        val builder = customNotificationCompat(context = context, pendingIntent = pendingIntent, event = event, args = args)

        try {
            val notificationManager = NotificationManagerCompat.from(context)
            if (notificationManager.areNotificationsEnabled())
                notificationManager.notify(deviceId.hashCode(), builder.build())
        } catch (e: SecurityException) {
            Log.d(TAG, "Notification permission not granted $e")
        }
    }

    private fun customNotificationCompat(context: Context, pendingIntent: PendingIntent, event: String, args: String) : NotificationCompat.Builder {
        val builder = NotificationCompat.Builder(context, SmartLiving.CHANNEL_ID)

        when(event) {
            "lockChanged" -> builder
                .setSmallIcon(R.drawable.puerta)
                .setContentTitle("Puerta")
                .setContentText(
                    if (args == "{newLock=locked}") {"BLOCKEADA CORRECTAMENTE"}
                    else {"SE ENCUENTRA DESBLOQUEDA"}
                         )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            "modeChanged" -> {
                val contentText =
                    if (args.contains("\"newMode\":\"party\"")) {"Modo 'Party'"}
                else {
                    if (args.contains("\"newMode\":\"vacation\"")){"Modo 'Vacation'"}
                        else {"Modo 'default'"}
                }
                builder
                .setSmallIcon(R.drawable.puerta)
                .setContentTitle("Heladera")
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            }

            else -> builder.setSmallIcon(R.drawable.logo).setContentTitle("SmartLiving").setContentText("Notification ...").setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true)
        }

        return builder
    }

companion object {
        private const val TAG = "ShowNotificationReceiver"
    }
}
