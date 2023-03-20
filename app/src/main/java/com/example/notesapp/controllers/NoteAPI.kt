package com.example.notesapp.controllers

import com.example.notesapp.models.Note


class NoteAPI() {

    private val notes = ArrayList<Note>()

    init {
        notes.add(Note("Note 1", "This is the first note", "2022-03-20", "2022-03-21"))
        notes.add(Note("Note 2", "This is the second note", "2022-03-21", "2022-03-22"))
        notes.add(Note("Note 3", "This is the third note", "2022-03-22", "2022-03-23"))
    }

    fun getNotes() = notes

    fun addNote(note:Note) = notes.add(note)

}
