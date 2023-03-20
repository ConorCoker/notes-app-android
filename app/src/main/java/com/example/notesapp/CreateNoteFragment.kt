package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.controllers.NoteAPI
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.models.Note
import java.util.Date

class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var notes = NoteAPI()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        setupClickListener()
        return binding.root
    }

    private fun setupClickListener() {
        binding.imageButtonCreateNote.setOnClickListener {
            if (allFieldsFilled()){
                notes.addNote(Note(binding.editTextNoteTitle.text.toString(),binding.editTextNoteContents.text.toString(),"",binding.editTextNoteExpectedCompletionDate.text.toString())) //need to sort out this
                Toast.makeText(context,"Now there is ${notes.getNotes().size} notes in system!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Please enter all fields!",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun allFieldsFilled() =
        binding.editTextNoteTitle.text.toString().isNotBlank() &&
                binding.editTextNoteContents.text.toString().isNotBlank() &&
                binding.editTextNoteExpectedCompletionDate.text.toString().isNotBlank()

}