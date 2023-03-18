package com.example.notesapp.models

import java.util.Date

data class Note(
    val title: String,
    val noteContents: String,
    val createdDate: Date,
    var lastsUntil: Date,
    val completedDate: Date
)