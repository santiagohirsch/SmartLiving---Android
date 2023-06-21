package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.myapplication.data.EventData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class EventService : Service() {

    companion object {
        private const val TAG = "EventService"
        private const val DELAY_MILLIS: Long = 10000
    }

    private lateinit var job: Job

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Starting service...")

        job = GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                val eventList = fetchEvents()
                // Aca decido que hacer con los eventos
                eventList?.forEach {
                    Log.d(TAG, "Broadcasting fetch notification intent (${it.deviceId})")
                    val intent2 = Intent().apply {
                        action = MyIntent.SHOW_NOTIFICATION
                        `package` = MyIntent.PACKAGE
                        putExtra(MyIntent.DEVICE_ID, it.deviceId)
                    }
                    sendOrderedBroadcast(intent2, null)
                }
                delay(DELAY_MILLIS)
            }
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d(TAG, "Stopping service...")
        super.onDestroy()

        if (job.isActive) job.cancel()
    }

    private fun fetchEvents(): List<EventData>? {
        //${BuildConfig.API_BASE_URL}
        val url = "${BuildConfig.API_BASE_URL}api/devices/events"

        val connection = (URL(url).openConnection() as HttpURLConnection).also {
            it.requestMethod = "GET"
            it.setRequestProperty("Accept", "application/gson")
            it.setRequestProperty("Content-Type", "text/event-stream")
            it.doInput = true
        }

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Log.d(TAG, "Connection successful...")
            val stream = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            val response = StringBuffer()
            val eventList = arrayListOf<EventData>()
            while (stream.readLine().also { line = it } != null) {
                when {
                    line!!.startsWith("data:") -> {
                        response.append(line!!.substring(5))
                    }
                    line!!.isEmpty() -> {
                        val gson = Gson()
                        val event = gson.fromJson<EventData>(
                            response.toString(),
                            object: TypeToken<EventData?>() {}.type
                        )
                        eventList.add(event)
                    }
                }
            }
            stream.close()
            Log.d(TAG, "Fetched ${eventList.size} events found...")
            return eventList
        } else {
            Log.d(TAG, "Error connecting or No new vents found...  ( ${responseCode} )")
            return null
        }
    }

}