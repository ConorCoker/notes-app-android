package com.example.notesapp.models

data class Note(
    val title: String,
    val noteContents: String,
    val createdDate: String,
    var lastsUntil: String,  //change to date when i read up on Date
)