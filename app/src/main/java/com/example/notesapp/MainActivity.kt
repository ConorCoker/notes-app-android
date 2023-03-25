package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.notesapp.controllers.NoteAPI
import com.example.notesapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.bottomNav.setOnItemSelectedListener(this)
        setContentView(binding.root)
    }

    override fun onNavigationItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.nav_create_note -> onCreateNoteClicked()
            R.id.nav_view_notes -> onViewNotesClicked()
            else -> false
        }

    private fun onViewNotesClicked(): Boolean {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.slide_in,0)
            replace(R.id.fragment_container, ViewNotesFragment()) }
        return true
    }

    private fun onCreateNoteClicked(): Boolean {
        supportFragmentManager.commit { replace(R.id.fragment_container, CreateNoteFragment()) }
        return true
    }
}



