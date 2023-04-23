package com.example.notesapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import com.example.notesapp.databinding.ActivityInDepthNoteBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteJSONStore
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class InDepthNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInDepthNoteBinding
    private lateinit var notes: NoteJSONStore
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInDepthNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notes = NoteJSONStore(this)
        note = notes.getNoteById(intent.getLongExtra("noteId", 0L))!!
        fillNoteDetails()
        setupButtons()
    }

    @SuppressLint("SimpleDateFormat")
    private fun setupButtons() {

        binding.buttonDeleteNote.setOnClickListener {
            notes.removeNoteById(note.id)
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.buttonSetReminder.setOnClickListener {
            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            val f = SimpleDateFormat("dd/MM/yyyy")
            try {
                val lastsUntil = f.parse(note.lastsUntil)
                intent.putExtra("endTime", lastsUntil!!.time)
                intent.putExtra("beginTime", Date())
                intent.putExtra(CalendarContract.Events.TITLE, note.title)
                startActivity(intent)
            } catch (e: ParseException) {
                Log.d("reminder", "caught $e when trying to set reminder")
                Toast.makeText(this, "Something went wrong: $e", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun fillNoteDetails() {

        binding.textViewTitle.text = note.title
        binding.textViewNoteContents.text = note.noteContents
        binding.textViewDateCreatedContents.text = note.createdDate
        if (note.isExpired()) {
            binding.textViewLastsUntilContents.setTextColor(Color.RED)
        }
        binding.textViewLastsUntilContents.text = note.lastsUntil

    }
}