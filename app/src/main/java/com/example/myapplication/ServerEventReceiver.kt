package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.myapplication.data.EventData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ServerEventReceiver : BroadcastReceiver() {

    private val gson = Gson()
    

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Alarming received...")

        GlobalScope.launch(Dispatchers.IO) {
            val eventList = fetchEvents()
            // Aca decido que hacer con los eventos
            val notifiableEvents = arrayOf("lockChanged", "modeChanged") // falta evento de aspiradora, que no nos lo genera la API!!!

            var eventsTotal = 0;
            eventList?.forEach {
                Log.d(TAG, "Broadcasting fetch notification intent (${it.deviceId}) (${it.event}) (${it.args})")

                if(notifiableEvents.contains(it.event)) {
                    val intent2 = Intent().apply {
                        action = MyIntent.SHOW_NOTIFICATION
                        `package` = MyIntent.PACKAGE
                        putExtra(MyIntent.DEVICE_ID, it.deviceId)
                        putExtra(MyIntent.EVENT, it.event)
                        putExtra(MyIntent.ARGS, it.args.toString())
                    }
                    context?.sendOrderedBroadcast(intent2, null)
                    eventsTotal += 1
                }
            }
            if (eventsTotal == 0) {
                Log.d(TAG, "Skipping notification broadcast window. $eventsTotal to show")
                abortBroadcast()
            }
        }
    }

    private fun fetchEvents(): List<EventData>? {
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
                        val event = gson.fromJson<EventData>(
                            response.toString(),
                            object: TypeToken<EventData?>() {}.type
                        )
                        eventList.add(event)
                        response.setLength(0)
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
    companion object {
        private const val TAG = "ServerEventReceive"
    }

}