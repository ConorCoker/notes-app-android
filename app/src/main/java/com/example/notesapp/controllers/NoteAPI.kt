package com.example.notesapp.controllers

import com.example.notesapp.models.Note
import java.util.*
import kotlin.collections.ArrayList


class NoteAPI private constructor() {

    private val notes: ArrayList<Note> = ArrayList()

    companion object {
        private var instance: NoteAPI? = null

        fun getInstance():NoteAPI{
            if (instance==null){
                instance = NoteAPI()
            }
            return instance!!
        }
    }

    fun getNotes() = notes

    fun addNote(note: Note) = notes.add(note)

    fun clearAllNotes(){
        notes.clear()
    }


}
