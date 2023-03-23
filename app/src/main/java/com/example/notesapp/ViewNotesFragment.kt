package com.example.notesapp

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider


import com.example.notesapp.controllers.NoteAPI
import com.example.notesapp.databinding.FragmentViewNotesBinding
import com.example.notesapp.models.Note

class ViewNotesFragment : Fragment() {

    private lateinit var binding: FragmentViewNotesBinding
    private var notes = NoteAPI
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewNotesBinding.inflate(inflater, container, false)
        setupOptionsMenu()
        setupRecyclerView()
        return binding.root
        
    }

    private fun setupOptionsMenu() {

        binding.myToolbar.inflateMenu(R.menu.menu_options)
        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.remove_all_notes -> {
                    notes.getInstance().clearAllNotes()
                    setupRecyclerView()
                    true
                }
                else -> false
            }
        }
    }
    private fun setupRecyclerView() {
        val notesAdapter = NoteAdapter(NoteAPI.getInstance().getNotes()) {
            moveToInDepthView(it)
        }
        binding.recyclerViewListNotes.adapter = notesAdapter
    }

    private fun moveToInDepthView(noteToView: Note) {

    }
}
