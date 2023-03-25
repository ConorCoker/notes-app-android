package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.controllers.NoteAPI
import com.example.notesapp.databinding.ActivityInDepthNoteBinding
import com.example.notesapp.models.Note

class InDepthNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInDepthNoteBinding
    private val notes = NoteAPI
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInDepthNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        note = intent.getSerializableExtra("note") as Note
        fillNoteDetails()
        setupDeleteButton()
    }

    private fun setupDeleteButton() {

        binding.buttonDeleteNote.setOnClickListener {
               notes.getInstance().removeNoteByTitle(note.title)
                startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun fillNoteDetails() {

        binding.textViewTitle.text = note.title
        binding.textViewNoteContents.text = note.noteContents
        binding.textViewDateCreatedContents.text = note.createdDate.toString()
        binding.textViewLastsUntilContents.text = note.lastsUntil.toString()
    }
}