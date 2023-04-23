package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.example.notesapp.controllers.NoteAPI
import com.example.notesapp.databinding.FragmentViewNotesBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteJSONStore

class ViewNotesFragment : Fragment() {

    private lateinit var binding: FragmentViewNotesBinding
    private lateinit var notes: NoteJSONStore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewNotesBinding.inflate(inflater, container, false)
        notes = NoteJSONStore(requireContext())
        setupOptionsMenu()
        setupRecyclerView()
        return binding.root

    }

    private fun setupOptionsMenu() {

        binding.myToolbar.inflateMenu(R.menu.menu_options)
        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.remove_all_notes -> {
                    notes.removeAll()
                    setupRecyclerView()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        val notesAdapter = NoteAdapter(notes.findAll() as ArrayList<Note>) {
            moveToInDepthView(it)
        }
        binding.recyclerViewListNotes.adapter = notesAdapter
    }

    private fun moveToInDepthView(noteToView: Note) {
        Log.d("click", "You have clicked note: ${noteToView.title}")
        val intent = Intent(context, InDepthNoteActivity::class.java)
        intent.putExtra("noteId", noteToView.id)
        startActivity(intent)
    }
}
