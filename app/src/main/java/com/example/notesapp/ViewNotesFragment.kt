package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.controllers.NoteAPI
import com.example.notesapp.databinding.FragmentViewNotesBinding
import com.example.notesapp.models.Note

class ViewNotesFragment : Fragment() {

    private lateinit var binding: FragmentViewNotesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewNotesBinding.inflate(inflater,container,false)
        Toast.makeText(context,"onCreateView() in view note has been called", Toast.LENGTH_LONG).show()
        setupRecyclerView()
        return binding.root
        
    }

    private fun setupRecyclerView() {
        val notesAdapter = NoteAdapter(NoteAPI.getInstance().getNotes()){
            moveToInDepthView(it)
        }
        binding.recyclerViewListNotes.adapter = notesAdapter
    }

    private fun moveToInDepthView(noteToView: Note) {

    }
}