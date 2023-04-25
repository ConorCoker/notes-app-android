package com.example.notesapp.helpers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.notesapp.models.NoteJSONStore

class AlarmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val noteId = intent?.getLongExtra("noteId",-1)
        Log.d("alarm","Alarm has been received for note $noteId")
        if (noteId == -1L) {
            return //if no long extra (noteId) is found
        }
        val note = NoteJSONStore(context!!).getNoteById(noteId!!)
        if (note!=null){
            Log.d("alarm","Attempting to create notification for $noteId")
            NotificationHelper(context).createNotification(note)
        }
    }
}