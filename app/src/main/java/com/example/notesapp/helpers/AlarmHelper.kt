package com.example.notesapp.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmHelper(private val context: Context) {

    private val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun setAlarm(noteId: Long, triggerTimeMillis: Long) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("noteId", noteId)
        }
        Log.d("alarm","Alarm set for note $noteId with trigger time of $triggerTimeMillis milliseconds")
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Set up an inexact alarm
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTimeMillis, pendingIntent)
    }

    fun cancelAlarm(noteId: Long) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("noteId", noteId)
        }
        Log.d("alarm","Attempting to delete alarm for note $noteId")
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)
    }
}